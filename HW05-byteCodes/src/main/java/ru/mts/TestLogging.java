package ru.mts;

public class TestLogging implements TestLoggingInterface {
    private int param;

    public TestLogging() {
    }

    public TestLogging(int param) {
        this.param = param;
    }

    public int getParam() {
        return param;
    }

    public void setParam(int param) {
        this.param = param;
    }

    public String toString(){ return  "TestLogging{" + " param = " + param + "}";}

    @Override
    @Log
    public void calculation(int param) {
        this.param = param;
        System.out.println("Executed method: calculation, int: " + param);
    }

    @Override
    public void calculation(String name) {
        System.out.println("Executed method: calculation, String: " + name);
    }

    @Override
    @Log
    public void calculation(String name, int param) {
        System.out.println("Executed method: calculation, String: " + name + " param int: " + param);
    }

    @Override
    public void calculationWithoutLog(int param) {
        System.out.println("Executed method: calculationWithoutLog, param: " + param);
    }
}
