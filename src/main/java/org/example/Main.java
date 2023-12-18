package org.example;

public class Main {
    public static void main(String[] args) {
        ThreadTest test = new ThreadTest();
        methodOne test1 = new methodOne();
        methodTwo test2 = new methodTwo();
        test1.methodOne();
        test2.methodTwo();
        test.methodThree(7);
    }
}