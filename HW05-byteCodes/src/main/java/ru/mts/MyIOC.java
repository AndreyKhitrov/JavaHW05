package ru.mts;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class MyIOC {
    static ArrayList<String> loggedMethods;
    public MyIOC() {
    }

    private static ArrayList<String> getAnnotationMethods(Method[] methods, Class classAnnotation) {
        ArrayList<String> varAnnotationMethod = new ArrayList(10);
        for (Method method : methods){
            if (method.isAnnotationPresent(classAnnotation)){
                varAnnotationMethod.add(method.getName());
            }
        }
        return  varAnnotationMethod;
    }

    static <T>  T createLogging(T object) throws Exception {
        InvocationHandler handler = new MyInvocationHandler(object);
        loggedMethods= getAnnotationMethods(object.getClass().getDeclaredMethods(), Log.class);
        return (T) Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), handler);

    }

    static class MyInvocationHandler<T> implements InvocationHandler {
        private final Object testLogging;

        public MyInvocationHandler(Object testLogging) {
            this.testLogging = testLogging;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (loggedMethods.contains(method.getName())){
                    for (Object o : args){
                        System.out.println("Log method: "+ method.getName()+ " and his argumets: " + o);
                    }
                }
                return method.invoke(testLogging, args);
        }
    }
}
