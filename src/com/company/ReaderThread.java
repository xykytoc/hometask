package com.company;

public class ReaderThread extends Thread {

    private Vector vector = null;

    public ReaderThread(Vector vector) {
        this.vector = vector;
    }

    @Override
    public void run() {
        setPriority(7);
        if (vector != null) {
            for (int i = 0; i < vector.getSize(); i++) {
                System.out.println(String.format("Read: %f from position %d",
                        vector.getElement(i), i));
            }
        }
    }
}
