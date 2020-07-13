package ru.mts;

public class Demo {
    public static void main(String[] args) throws Exception {
        TestLoggingInterface original = new TestLogging();
        TestLoggingInterface loggable = (TestLoggingInterface) MyIOC.createLogging(original);

        loggable.calculation(6);

        loggable.calculationWithoutLog(6);
        OneMoreClassInterface originalTest = new OneMoreClass();
        OneMoreClassInterface loggableTest = (OneMoreClassInterface) MyIOC.createLogging(originalTest);

        loggableTest.methodLog(10);

    }
}
