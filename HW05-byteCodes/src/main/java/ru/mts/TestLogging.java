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

    @Override
    public void calculation(int param) {
        this.param = param;
        System.out.println("Executed method: calculation, param: " + param);
    }

    ;
}
