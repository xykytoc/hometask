package com.company;

public class SafePrinter implements Runnable {

    public void run() {
        SafeThreadVector vx = new SafeThreadVector(
                new LinkedListVector(25));
        vx.print();
    }
}