import annotation.EnableYaml;
import com.google.common.primitives.Primitives;
import jodd.introspector.ClassDescriptor;
import jodd.introspector.ClassIntrospector;
import jodd.introspector.FieldDescriptor;
import jodd.introspector.PropertyDescriptor;
import model.RedisConfig;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.*;

/**
 * Created by Administrator on 2017/5/25.
 * yaml的转换工具类
 *
 */
public class YamlProcess {


    public static void main(String[] args) {
        /**
         * 使用方式
         * 需要在配置类中添加注解EnableYaml
         * 并且注解中的prfix和path是必须的
         * prefix表示属性在yaml文件中的全路径比如：a.b.c.RedisConfig
         * path表示文件的名称 比如：redis.yaml
         * 支持list和嵌套类型  比如：list: lxl, lxl01, lxl02
         * list集合必须以，分割，不然无法解析为正确的list集合
         * 使用方式如下所示
         */
        RedisConfig config = process(RedisConfig.class);
        System.out.println(config.toString());

    }

    private static Map<String, LinkedHashMap> cache = new HashMap<>();

    private YamlProcess() {
    }

    public static <T> T process(Class<T> tClass) {
        T t = null;
        try {
            t = tClass.newInstance();
            EnableYaml yaml = tClass.getAnnotation(EnableYaml.class);
            LinkedHashMap map = loadAll(yaml);//所有属性map集合
            LinkedHashMap prefixMap = getPrefixMap(map, yaml);//map的prfix集合
            initBean(prefixMap, t, RedisConfig.class);//bean设置
        } catch (Exception e) {
            System.err.println("无法转换yaml文件");
        }
        return t;
    }


    /**
     * 设置bean属性
     *
     * @param map
     * @param config
     */
    private static void initBean(LinkedHashMap map, Object config, Class target) throws Exception {
        ClassDescriptor cd = ClassIntrospector.lookup(target);
        PropertyDescriptor[] pds = cd.getAllPropertyDescriptors();
        for (int i = 0; i < pds.length; i++) {
            FieldDescriptor descriptor = pds[i].getFieldDescriptor();
            Object value = map.get(descriptor.getName());
            if (value == null) {
                continue;
            }
            Class type = descriptor.getRawType();
            boolean isPrimitive = isPrimitive(type) || type.getName().equals("java.lang.String");
            if (isPrimitive) {//基本数据类型或者是string类型
                descriptor.invokeSetter(config, value);
            } else if (type.isAssignableFrom(List.class)) {//list集合，必须使用，分割
                List data = Arrays.asList(value.toString().split(","));
                descriptor.invokeSetter(config, data);
            } else {
                //引用类型
                LinkedHashMap objMap = (LinkedHashMap) value;
                Object instance = type.newInstance();
                initBean(objMap, instance, type);
                descriptor.invokeSetter(config, instance);
            }
        }
    }

    /**
     * 是否是包装类型或者是基本数据类型
     *
     * @return
     */
    private static boolean isPrimitive(Class type) {
        boolean a = type.isPrimitive();
        Set<Class<?>> set = Primitives.allWrapperTypes();
        return set.contains(type) || a;
    }


    /**
     * 获取所有
     *
     * @param yaml
     * @return
     */
    private static LinkedHashMap loadAll(EnableYaml yaml) {
        String key = yaml.path() + "#" + yaml.prefix();
        if (cache.get(key) != null) {
            return cache.get(key);
        }
        Yaml ya = new Yaml();
        ClassLoader loader = YamlProcess.class.getClassLoader();
        InputStream is = loader.getResourceAsStream(yaml.path());
        LinkedHashMap map = (LinkedHashMap) ya.load(is);
        cache.put(key, map);
        return map;
    }

    /**
     * 获取上级
     *
     * @param map
     * @param yaml
     * @return
     */
    private static LinkedHashMap getPrefixMap(LinkedHashMap map, EnableYaml yaml) {
        String key = yaml.path() + "#" + yaml.prefix() + "#prefix";
        if (cache.get(key) != null) {
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
