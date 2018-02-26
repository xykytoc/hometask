package com.company;

import java.util.Iterator;

public class SafeThreadVector implements Vector {

    private final Object lock = new Object();
    private Vector vector = null;

    public SafeThreadVector(Vector vector) {
        this.vector = vector;
    }

    public VectorType getVectorType() {
        synchronized (lock) {
            return vector.getVectorType();
        }
    }

    public int getSize() {
        synchronized (lock) {
            return vector.getSize();
        }
    }

    public double getElement(int index) throws VectorIndexOutOfBoundsException {
        synchronized (lock) {
            return vector.getElement(index);
        }
    }

    public void setElement(int index, double value) throws VectorIndexOutOfBoundsException {
        synchronized (lock) {
            vector.setElement(index, value);
        }
    }

    public void addElement(double value) {
        synchronized (lock) {
            vector.addElement(value);
        }
    }

    public void removeElement(int index) {
        synchronized (lock) {
            vector.removeElement(index);
        }
    }

    public void print() {
        if (Vectors.useOutLock) {
            synchronized (System.out) {
                vector.print();
            }
        } else {
            vector.print();
        }
    }

    public Iterator iterator() {
        return this.vector.iterator();
    }
}
