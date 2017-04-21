package reflect;

import com.google.common.collect.Lists;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/21.
 */
public class Test {
    public static List<String> list1 = Lists.newArrayList();

    public static void main(String[] args) throws Exception {

        //获取UserDao中的泛型类型
        //1java
//        Class c = UserDao.class;
//        UserDao<String> baseDao = new UserDao<String>();
//        Type[] t = c.getGenericInterfaces();
//        Arrays.stream(t).forEach((a) -> {
//            ParameterizedType type = (ParameterizedType)a;
//            System.out.println(type.getActualTypeArguments()[0].getTypeName());
//        });
//        Type t = baseDao.getClass().getGenericSuperclass();
//        ParameterizedType type = (ParameterizedType)t;
//        System.out.println(type.getActualTypeArguments()[0].getTypeName());//java.lang.String
//        System.out.println("------------------------------------------------");
//        //guava中的方式
//        TypeToken typeToken = TypeToken.of(BaseDao.class);
//        TypeToken typeToken1 = typeToken.getSubtype(UserDao.class);
//        typeToken1.getTypes().stream().forEach((b) -> System.out.println(b.toString()));
//        TypeToken typeToken2 = new IKnowMyType<Date>() {}.type;
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//        System.out.println(typeToken2.getRawType().getName());
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
////        typeToken1.getTypes().rawTypes().stream().forEach((b) -> System.out.println(b.toString()));


//        ArrayList<String> list = Lists.newArrayList();
        //获取类的泛型
//        Type[] ts = list.getClass().getGenericInterfaces();
//        Arrays.stream(ts).forEach((a) -> {
//            if( a instanceof ParameterizedType){
//                ParameterizedType type = (ParameterizedType) a;
//                Arrays.stream(type.getActualTypeArguments()).forEach((t) -> System.out.println(t.toString()));//E
//            }
//        });

        //获取字段的泛型
//        ParameterizedType p = (ParameterizedType)Test.class.getField("list1").getGenericType();
//        Arrays.stream(p.getActualTypeArguments()).forEach((a) -> System.out.println(a.toString()));//java.lang.String
//
//
//
//        Type ts = list.getClass().getGenericSuperclass();
//        Arrays.stream(ts).forEach((a) -> {
//            if( ts instanceof ParameterizedType){
//                ParameterizedType type = (ParameterizedType) ts;
//                Arrays.stream(type.getActualTypeArguments()).forEach((t) -> System.out.println(t.toString()));//E
//            }
//        });

        //Guava 获取泛型
        TypeToken<ArrayList<String>> typeToken = new IKnowMyType<ArrayList<String>>(){}.type;
        TypeToken<Map<Integer, String>> typeToken1 = new IKnowMyType<Map<Integer, String>>(){}.type;

//        System.out.println(typeToken.getSupertype(aaa.getClass()));
//        TypeToken<ArrayList<String>> typeToken = new TypeToken<ArrayList<String>>(){};
//        typeToken1
        TypeToken keyToken = typeToken1.resolveType(Map.class.getTypeParameters()[0]);
        TypeToken valueToken = typeToken1.resolveType(Map.class.getTypeParameters()[1]);
        System.out.println(keyToken.getType().getTypeName());
        System.out.println(valueToken.getType().getTypeName());
        //invokable
        Class clazz = UserDao.class;
//        Method m = clazz.getMethod("say");

//        Invokable invokable = Invokable.from(m);
//        !(Modifier.isFinal(method.getModifiers())
//                || Modifiers.isPrivate(method.getModifiers())
//                || Modifiers.isStatic(method.getModifiers())
//                || Modifiers.isFinal(method.getDeclaringClass().getModifiers()))
//        invokable.isOverridable();//是否可以被重写
//
////        !(Modifier.isPrivate(method.getModifiers()) || Modifier.isPublic(method.getModifiers()))
//        invokable.isPackagePrivate();//是否是包被私有方法
//        invokable.getParameters();//获取方法参数列表
        Reflection.newProxy(BaseDao.class, (proxy, method,  ar) -> {return null;});//生成jdk代理
        ClassPath.from(ClassLoader.getSystemClassLoader()).getAllClasses().stream().forEach((a) -> {
//            System.out.println(a.getName());
        });
//        ClassPath.from(ClassLoader.getSystemClassLoader()).getTopLevelClasses("reflect").stream().forEach((a) -> System.out.println(a.getName()));


        ClassPath.from(ClassLoader.getSystemClassLoader()).getTopLevelClassesRecursive("reflect").stream().forEach((a) -> System.out.println(a));


    }




}
