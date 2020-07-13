package ru.mts;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class MyIOC {
    static ArrayList<Method> loggedMethods;
    public MyIOC() {
    }

    private static ArrayList<Method> getAnnotationMethods(Method[] methods, Class classAnnotation) {
        ArrayList<Method> varAnnotationMethod = new ArrayList(10);
        for (Method method : methods){
            if (method.isAnnotationPresent(classAnnotation)){
                varAnnotationMethod.add(method);
            }
        }
        return  varAnnotationMethod;
    }

    static Object createLogging(Object object) throws Exception {
        InvocationHandler handler = new MyInvocationHandler(object);
        loggedMethods= getAnnotationMethods(object.getClass().getDeclaredMethods(), Log.class);
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), handler);

    }

    static class MyInvocationHandler implements InvocationHandler {
        private final Object testLogging;

        public MyInvocationHandler(Object testLogging) {
            this.testLogging = testLogging;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            for (Method method1 : loggedMethods) {
                if (method1.getName()==method.getName()){
                    System.out.println("Log method: "+ method.getName()+ " and his argumets: " + args[0]);
                }
            }
                return method.invoke(testLogging, args);
        }
    }
}
