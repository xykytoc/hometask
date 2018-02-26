package com.company;

public class ArrayVectorFactory implements VectorFactory {

    public Vector createInstance(int length) {
        return new ArrayVector(length);
    }
}