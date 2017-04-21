package test.reflect;


import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/4/21.
 */
public class TestReflectUtil {

    public static void main(String[] args) throws Exception {


//        Class clazz = Class.forName("test.reflect.BaseDao");

        Class clazz = UserDao.class;
        Method[] ms = clazz.getDeclaredMethods();
        Arrays.stream(ms).forEach((a) -> {
            Resource r = a.getAnnotation(Resource.class);
            if(r != null){
//                System.out.println(r.value());
            }
        });

        List<String> list = new ArrayList<>();
        list.add("A");// 由于addAll期望获得Collection<? extends String>类型的参数，因此下面的语句无法通过
        list.addAll(new ArrayList<>());

        Type[] types = clazz.getGenericInterfaces();
        Arrays.stream(types).forEach((a) -> {
//            System.out.println(a.getTypeName());
        });
        Arrays.stream(types).forEach((a) -> {
            ParameterizedType p=(ParameterizedType)a;
            System.out.println(p.getTypeName());
            Arrays.stream(p.getActualTypeArguments()).forEach((b) -> System.out.println(b));
            System.out.println(p.getOwnerType());
            System.out.println(p.getRawType());
        });



    }


}
