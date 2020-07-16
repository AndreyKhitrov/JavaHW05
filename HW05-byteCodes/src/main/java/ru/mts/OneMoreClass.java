package ru.mts;

public class OneMoreClass implements OneMoreClassInterface {

    @Override
    @Log
    public void methodLog(int param) {
        System.out.println("OneMoreClass: " + param);
    }

    @Override
    public void methodLog1(int param, String param1) {
        System.out.println("OneMoreClass with first parammeter: " + param + " and second paremeter: " + param1);
    }
}

