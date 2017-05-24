import annotation.EnableYaml;
import jodd.introspector.ClassDescriptor;
import jodd.introspector.ClassIntrospector;
import jodd.introspector.FieldDescriptor;
import jodd.introspector.PropertyDescriptor;
import model.RedisConfig;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.*;

/**
 * Created by Administrator on 2017/5/24.
 */
public class Main {


    private static Map<String, LinkedHashMap> cache = new HashMap<>();

    public static void main(String[] args) {
        RedisConfig config = new RedisConfig();
        EnableYaml yaml = RedisConfig.class.getAnnotation(EnableYaml.class);
        LinkedHashMap map = loadAll(yaml);//所有属性map集合
        LinkedHashMap prefixMap = getPrefixMap(map, yaml);//map的prfix集合
        initBean(prefixMap, config);//bean设置
        System.out.println(config.toString());
    }

    /**
     * 设置bean属性
     * @param map
     * @param config
     */
    private static void initBean(LinkedHashMap map, RedisConfig config){
        ClassDescriptor cd = ClassIntrospector.lookup(RedisConfig.class);
        PropertyDescriptor[] pds = cd.getAllPropertyDescriptors();
        try {
            for (int i = 0; i < pds.length; i++) {
                FieldDescriptor descriptor = pds[i].getFieldDescriptor();
                Class type = descriptor.getRawType();
                boolean isPrimitive = type.isPrimitive() || type.getName().equals("java.lang.String");
                if(isPrimitive){
                    descriptor.invokeSetter(config, map.get(descriptor.getName()));
                } else if(type.isAssignableFrom(List.class)) {//list集合，必须使用，分割
                    List data = Arrays.asList(map.get(descriptor.getName()).toString().split(","));
                    descriptor.invokeSetter(config, data);
                } else {
                    //引用类型


                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }



    /**
     * 获取所有
     * @param path
     * @return
     */
    private static LinkedHashMap loadAll(EnableYaml yaml) {
        String key = yaml.path() + "#" + yaml.prefix();
        if(cache.get(key) != null){
            return cache.get(key);
        }
        Yaml ya = new Yaml();
        ClassLoader loader = Main.class.getClassLoader();
        InputStream is = loader.getResourceAsStream(yaml.path());
        LinkedHashMap map = (LinkedHashMap) ya.load(is);
        cache.put(key, map);
        return map;
    }

    /**
     * 获取上级
     * @param map
     * @param prefix
     * @return
     */
    private static LinkedHashMap getPrefixMap(LinkedHashMap map, EnableYaml yaml) {
        String key = yaml.path() + "#" + yaml.prefix() + "#prefix";
        if(cache.get(key) != null){
            return cache.get(key);
        }
        String[] strs = yaml.prefix().split("\\.");
        Object result = null;
        for (int i = 0; i < strs.length; i++) {
            result = map.get(strs[i]);
            if (result instanceof LinkedHashMap) {
                map = (LinkedHashMap) result;
            } else {
                break;
            }
        }
        cache.put(key, map);
        return map;
    }


}
