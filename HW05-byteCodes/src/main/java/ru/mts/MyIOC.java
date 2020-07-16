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

    private static List<Method> getAnnotationMethods(Object object, Class classAnnotation) {
        ArrayList<Method> varAnnotationMethod = new ArrayList(10);
        for (Method method : object.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(classAnnotation)) {
                varAnnotationMethod.add(method);
            }
        }
        return (List<Method>) varAnnotationMethod;
    }

    static <T> T createLogging(T object) throws Exception {
        InvocationHandler handler = new MyInvocationHandler(object);
        loggedMethods = (ArrayList<Method>) getAnnotationMethods(object, Log.class);
        return (T) Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), handler);

    }

    static class MyInvocationHandler<T> implements InvocationHandler {
        private final T testLogging;

        public MyInvocationHandler(T testLogging) {
            this.testLogging = testLogging;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            for (Method varMethod : loggedMethods) {
                int i = 0;
                int priz = 0;
                if (varMethod.getName().equals(method.getName()) & varMethod.getParameterTypes().length == method.getParameterTypes().length) {
                    for (Class<?> cl : varMethod.getParameterTypes()) {
                        if (cl.getTypeName().equals(method.getParameterTypes()[i].getTypeName())) {
                            priz++;
                        }
                        i++;

                    }
                    if (i == priz & i != 0) {
                        for (Object o : args)
                            System.out.println("++++++++++Log method: " + method.getName() + " and his argumets: " + o);
                    }
                }
            }
                return method.invoke(testLogging, args);
        }
    }
}
