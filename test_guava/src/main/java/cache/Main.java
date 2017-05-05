package cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/5/4.
 */
public class Main {

    public static void main(String[] args) throws Exception {

        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterAccess(4, TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>(){
                    @Override
                    public String load(String key) throws Exception {
                        System.out.println("重新查询了");
                        return key + ".value";
                    }
                });

        String s = cache.get("abc");
        System.out.println(s);
        String s1 = cache.get("abc");
        Thread.sleep(5000);
        String s2 = cache.get("abc");
        String s3 = cache.get("abc");
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);



    }
}
