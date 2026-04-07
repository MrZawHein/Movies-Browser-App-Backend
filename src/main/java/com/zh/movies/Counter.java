package com.zh.movies;

public class Counter {

    private static int counter = 0;

    public Counter(){
        Counter.counter++;
    }

    static int getCount(){
        return Counter.counter;
    }

    public static void main(String[] args) {
        Counter a = new Counter();
        Counter b = new Counter();

        System.out.println(Counter.counter);
    }
}
