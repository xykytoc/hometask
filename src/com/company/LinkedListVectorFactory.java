package com.company;

public class LinkedListVectorFactory implements VectorFactory {

    public Vector createInstance(int length) {
        return new LinkedListVector(length);
    }
}