package com.company;

import java.util.Random;

public class WriterThread extends Thread {

    private Vector vector = null;

    public WriterThread(Vector vector) {
        this.vector = vector;
    }

    @Override
    public void run() {
        setPriority(9);
        Random rand = new Random();
        if (vector != null) {
            for (int i = 0; i < vector.getSize(); i++) {
                double value = rand.nextDouble() * 100;
                vector.setElement(i, value);
                System.out.println(String.format("Write: %f to position %d",
                        value, i));
            }
        }
    }
}
