package com.test.vtor;

import jodd.introspector.ClassDescriptor;
import jodd.introspector.ClassIntrospector;
import jodd.introspector.FieldDescriptor;
import jodd.introspector.PropertyDescriptor;

/**
 * Created by Administrator on 2017/5/24.
 */
public class TestOther {

    public static void main(String[] args) throws Exception {

        User user = new User("1", "2", "3");

        ClassDescriptor descriptor = ClassIntrospector.lookup(User.class);
        PropertyDescriptor[] descriptors = descriptor.getAllPropertyDescriptors();
        FieldDescriptor fieldDescriptor = descriptor.getFieldDescriptor("name", true);

        System.out.println(fieldDescriptor.invokeGetter(user));

//        Arrays.stream(descriptors).forEach((a) -> {
////            System.out.println(a.getName());
//            try {
//                if(a.getName().equals("name")){
//                    a.getSetter(true).invokeSetter(user, "lxl");
//                }
//                System.out.println(a.getName() + ":" + a.getGetter(true).invokeGetter(user));
//            } catch (InvocationTargetException | IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        });
//
//        Class clazz = User.class;
//        Field[] fields = clazz.getFields();
//
//        for(Field field : fields){
//
//            String fieldName = field.getName();
//            String setMethodName = "set" + fieldName.toUpperCase().charAt(0) + fieldName.substring(1, fieldName.length() - 1);
//            Method setMethod = clazz.getMethod(setMethodName, null);
//            setMethod.invoke(user, "lxl");
//
//
//        }


    }
}
