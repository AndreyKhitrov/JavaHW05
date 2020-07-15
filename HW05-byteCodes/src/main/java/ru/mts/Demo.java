package ru.mts;

public class Demo {
    public static void main(String[] args) throws Exception {
        TestLoggingInterface original = new TestLogging();
        TestLoggingInterface loggable = MyIOC.createLogging(original);

        loggable.calculation(6);

        loggable.calculationWithoutLog(6);
        OneMoreClassInterface originalTest = new OneMoreClass();
        OneMoreClassInterface loggableTest = MyIOC.createLogging(originalTest);

        loggableTest.methodLog(10);
        loggableTest.methodLog1(123, loggableTest.getClass().getName());

    }
}
