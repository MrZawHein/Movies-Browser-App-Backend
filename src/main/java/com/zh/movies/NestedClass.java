package com.zh.movies;

import org.aspectj.apache.bcel.classfile.InnerClass;

public class NestedClass {
    int outere = 10;

    void outer(){
        Inner inner = new Inner();
        inner.mehtod();
    }

    class Inner{
        void mehtod(){
            System.out.println("Outer . outer "+ outere);
        }
    }

    public static void main(String[] args) {
        NestedClass nestedClass = new NestedClass();
        nestedClass.outer();
    }
}
