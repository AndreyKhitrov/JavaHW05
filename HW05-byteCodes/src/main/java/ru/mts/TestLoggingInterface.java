package ru.mts;

public interface TestLoggingInterface<T> {
    public void calculation(int param);

    public void calculation(String name);

    public void calculation(String name,int param);

    public void calculationWithoutLog(int param);
}
