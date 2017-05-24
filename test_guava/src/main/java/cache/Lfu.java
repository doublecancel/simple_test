package cache;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Administrator on 2017/5/11.
 */
public class Lfu {


    private static Map<String, CacheObject<String>> map = null;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private int size;

    public static Lfu of(int size){
        Lfu l = new Lfu();
        l.size = size;
        l.map = new HashMap<String, CacheObject<String>>();
        return l;
    }

    private void set(String key, CacheObject value){
        lock.writeLock().lock();
        if(isFull()){
            int count = removeLeastUse();
            System.out.println(count);
        }
        if(!isFull()){
            map.put(key, value);
        }
        lock.writeLock().unlock();
    }

    private String get(String key){
        lock.writeLock().lock();
        String s = Optional.ofNullable(map.get(key)).map((a) -> a.getObject()).orElse("");
        lock.writeLock().unlock();
        return s;
    }

    /**
     * 判断是否已满
     * @return
     */
    private boolean isFull(){
        return map.size() >= this.size;
    }

    /**
     * 移除使用次数最少
     */
    private int removeLeastUse(){
        lock.writeLock().lock();
        int count = 0;//移除的总数
        Iterator it = map.values().iterator();

        CacheObject leastUserObj = null;

        while(it.hasNext()){
            CacheObject obj = (CacheObject)it.next();
            if(obj.isExpire()){
                it.remove();
                count++;
                continue;
            }
            if(leastUserObj == null || leastUserObj.accessCount > obj.accessCount){
                leastUserObj = obj;
            }
        }
        if(!isFull()){
            return count;
        }
        it = map.values().iterator();
        while(it.hasNext()){
            CacheObject obj = (CacheObject)it.next();
            obj.accessCount = obj.accessCount - leastUserObj.accessCount;
            if(obj.accessCount <= 0){
                it.remove();
                count++;
            }
        }
        lock.writeLock().unlock();
        return count;
    }

    private static class CacheObject<T> {
        private T value;//值
        private int accessCount;//访问次数
        private long lastAccessTime;//上次访问次数
        private long ttl;//保存时间
        public static <T> CacheObject of(T value, long ttl){
            CacheObject obj = new CacheObject();
            obj.value = value;
            obj.ttl = ttl;
            obj.lastAccessTime = System.currentTimeMillis();
            return obj;
        }
        public T getObject(){
            this.accessCount++;//访问次数+1
            return value;
        }

        /**
         * 是否过期
         * true未过期
         * false已过期
         * @return
         */
        public boolean isExpire(){
            return false;
        }

    }


    public static void main(String[] args) {



        Lfu lfu = Lfu.of(3);
        lfu.set("a", CacheObject.of("aaa", 1000000));
        lfu.set("b", CacheObject.of("bbb", 1000000));
        lfu.set("c", CacheObject.of("ccc", 1000000));


        lfu.get("b");
        lfu.get("b");
        lfu.get("a");

        lfu.set("d", CacheObject.of("ddd", 1000000));
        lfu.set("e", CacheObject.of("eee", 1000000));


        System.out.println(lfu.get("a"));
        System.out.println(lfu.get("b"));
        System.out.println(lfu.get("c"));
        System.out.println(lfu.get("d"));
        System.out.println(lfu.get("e"));


    }



}
