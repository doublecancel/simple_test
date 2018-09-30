package cache;

import com.google.common.cache.*;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class LoadingCache2 {

    static Map<String, String> data = new HashMap<>();

    @Before
    public void init(){
        data.put("a", "1");
        data.put("b", "2");
        data.put("c", "3");
        data.put("d", "4");
        data.put("e", "5");
        data.put("f", "6");
        data.put("g", "7");
        data.put("h", "8");
        data.put("i", "9");
        data.put("j", "10");
        data.put("k", "11");
        data.put("l", "12");
    }


    @Test
    public void testSimple() throws ExecutionException {

        Cache<String, String> loader = CacheBuilder.newBuilder()
                .build();

        loader.put("a", "b");
        assertEquals("b", loader.getIfPresent("a"));
        assertNull(loader.getIfPresent("b"));

        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder()
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws FileNotFoundException {
//                        return data.get(key);
                        File file = new File("");
                        FileInputStream ns = new FileInputStream(file);
                        return data.get(key);
                    }
                });

        loadingCache.put("a", "new");
        assertEquals("new", loadingCache.getUnchecked("a"));
        assertNull(loadingCache.getIfPresent("t"));

        System.out.println(loadingCache.getUnchecked("r"));
        //get 方法会将检查型的异常转换为ExecutingException,非检查型异常转换为UncheckedExecutingException
        //getUncheck 方法将检查型和非检查型转换为UnCheckedExecutingException

    }


    @Test
    public void testExpireAfterWrite() throws InterruptedException, ExecutionException {

        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        System.out.println("load----------------------");
                        return data.get(key);
                    }
                });
        //getIfPresent只会在缓存中寻找不会调用load方法，只有get和getUncheked方法才会调用
        assertEquals("1", cache.get("a"));
        TimeUnit.SECONDS.sleep(11);
        assertNull(cache.getIfPresent("a"));

    }

    @Test
    public void testExpireAfterAccess() throws Exception {

        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return data.get(key);
                    }
                });
        LoadingCache<String, String> excache = CacheBuilder.newBuilder()
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .expireAfterAccess(5, TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return data.get(key);
                    }
                });
        assertEquals("1", cache.getUnchecked("a"));
        assertEquals("1", excache.getUnchecked("a"));

        TimeUnit.SECONDS.sleep(11);
        assertNull(cache.getIfPresent("a"));
        assertNull(excache.getIfPresent("a"));

        cache.getUnchecked("a");
        excache.getUnchecked("a");
        TimeUnit.SECONDS.sleep(8);
        cache.getUnchecked("a");
        excache.getUnchecked("a");
        TimeUnit.SECONDS.sleep(3);

        assertNull(cache.getIfPresent("a"));
        assertEquals("1", excache.getIfPresent("a"));

    }

    
    @Test
    public void sizeCache(){
        
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(3)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return data.get(key);
                    }
                });
        
        cache.getUnchecked("a");
        cache.getUnchecked("b");
        cache.getUnchecked("c");
        cache.asMap().forEach((k, v) -> System.out.println("key:" + k + ",value:" + v));
        cache.getUnchecked("d");//当超出了数量控制时，第一个添加的元素会被删除
        System.out.println("-----------");
        cache.asMap().forEach((k, v) -> System.out.println("key:" + k + ",value:" + v));
    }


    @Test
    public void testClear(){
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .recordStats()
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return data.get(key);
                    }
                });
        cache.getUnchecked("a");
        cache.getUnchecked("b");
        cache.getUnchecked("c");
        assertEquals("1", cache.getIfPresent("a"));
        assertEquals("2", cache.getIfPresent("b"));
        assertEquals("3", cache.getIfPresent("c"));

//        cache.invalidateAll();
//        assertNull(cache.getIfPresent("a"));
//        assertNull(cache.getIfPresent("b"));
//        assertNull(cache.getIfPresent("c"));
        cache.cleanUp();//cleanUp方法并不会将缓存删除，而Invalidate方法会
//        assertNull(cache.getIfPresent("a"));
//        assertNull(cache.getIfPresent("b"));
//        assertNull(cache.getIfPresent("c"));
        cache.invalidate("a");
        assertNull(cache.getIfPresent("a"));

    }

    @Test
    public void recordStas() throws InterruptedException {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .recordStats()
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return data.get(key);
                    }
                });
        cache.getUnchecked("a");
        IntStream.rangeClosed(1, 100).forEach(a -> {
            cache.getUnchecked("a");
            CacheStats stats = cache.stats();
            long hitCount = stats.hitCount();
            long requestCount = stats.requestCount();
            assertEquals(a, hitCount);
            assertEquals(a + 1, requestCount);
        });
        System.out.println("命中率：" + cache.stats().hitRate());

        cache.getUnchecked("a");
        cache.getUnchecked("b");
        TimeUnit.SECONDS.sleep(10);

        long evictionCount = cache.stats().evictionCount();

    }
    
    @Test
    public void testRemoveListener() throws InterruptedException {
        LoadingCache<String, String> timeCache = CacheBuilder.newBuilder()
                .maximumSize(5)
                .expireAfterAccess(3, TimeUnit.SECONDS)
                .removalListener(notification -> {
                    switch (notification.getCause()){
                        case SIZE:
                            System.out.println("size");
                            return;
                        case EXPIRED://超时并不会立即调用该回调方法
                            System.out.println("expired");
                            return;
                        case EXPLICIT:
                            System.out.println("explticit");
                            return;
                        case REPLACED:
                            System.out.println("replaced");
                            return;
                        case COLLECTED:
                            System.out.println("collected");
                            return;
                            default:
                    }
                })
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return data.get(key);
                    }
                });


//        data.forEach((k, v) -> {
//            timeCache.getUnchecked(k);
//            try {
//                TimeUnit.SECONDS.sleep(4);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(timeCache.getIfPresent(k));//expire并不会立即调用.只有在下次执行get或者是getUnchecked方法时才会调用回调监听
//            timeCache.getUnchecked(k);//或者是在读取的时候少量的执行
//            System.out.println("--------------------------");
//        });
//        data.forEach((k, v) -> {
//            timeCache.getUnchecked(k);
//            timeCache.invalidate(k);//会调用explticit
//        });

//        data.forEach((k, v) -> {
//            timeCache.getUnchecked(k);//会立即调用size
//        });

        data.forEach((k, v) -> {
            timeCache.getUnchecked("a");
            timeCache.put("a", "1");//会立即调用replace，并且如果是相同的值也会触发这个回调
        });
        ExecutorService pool = Executors.newFixedThreadPool(4);
        RemovalListener<Object, Object> asynchronous = RemovalListeners.asynchronous(notification -> {
            //异步执行

        }, pool);

    }

    @Test
    public void testThread(){
        ExecutorService pool = Executors.newFixedThreadPool(4);
        pool.submit(() -> {

        });


        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .concurrencyLevel(4)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return data.get(key);
                    }
                });





    }

    @Test
    public void testRefresh(){

        LoadingCache<String, String> timeCache = CacheBuilder.newBuilder()
                .maximumSize(5)
                .expireAfterAccess(3, TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return data.get(key);
                    }
                });

        timeCache.put("a", "b");
        System.out.println(timeCache.getIfPresent("a"));

        timeCache.refresh("b");//主动调用load方法，如果存在则直接覆盖，如果不存在则保存原来的值

        System.out.println(timeCache.getIfPresent("b"));
    }





}
