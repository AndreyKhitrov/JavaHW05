package ru.mts;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyIOC {

    public MyIOC() {
    }

    static TestLoggingInterface createTestLogging() throws Exception {
        TestLogging myObject = new TestLogging();
        InvocationHandler handler = new MyInvocationHandler(myObject);
        return (TestLoggingInterface) Proxy.newProxyInstance(myObject.getClass().getClassLoader(), myObject.getClass().getInterfaces(), handler);

    }

    static class MyInvocationHandler implements InvocationHandler {
        private final TestLoggingInterface TestLogging;

        public MyInvocationHandler(TestLoggingInterface testLogging) {
            TestLogging = testLogging;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Annotation[] annotations = method.getDeclaredAnnotations();
            for (Annotation annotation : annotations){
                if (annotation.annotationType().equals(Log.class)){
                    System.out.println("Executed method " + method.getName() + " param: " + args[0]);
                    return method.invoke(TestLogging, args);
                }
            }
                return null;
        }
    }
}
