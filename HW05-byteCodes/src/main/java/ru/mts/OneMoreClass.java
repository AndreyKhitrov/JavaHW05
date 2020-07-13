package ru.mts;

public class OneMoreClass implements OneMoreClassInterface {

    @Override
    @Log
    public void methodLog(int param) {
        System.out.println("OneMoreClass: " + param);
    }
}
