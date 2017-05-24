package cache;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Administrator on 2017/5/11.
 */
public class Lru {

    private static Map<String, String> map;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public static Lru of(int size){
        map = new LinkedHashMap<String, String>(size){
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                return size() > size;
            }
        };
        return new Lru();
    }
    private void set(String key, String value){
        lock.writeLock().lock();
        map.put(key, value);
        lock.writeLock().unlock();
    }
    private String get(String key){
        lock.readLock().lock();
        String s = map.get(key);
        lock.readLock().unlock();
        return s;

    }








    public static void main(String[] args) {

        Lru lru = Lru.of(3);
        lru.set("a", "a");
        lru.set("b", "b");
        lru.set("c", "c");
        lru.set("d", "d");
        System.out.println(lru.get("c"));




    }
}
