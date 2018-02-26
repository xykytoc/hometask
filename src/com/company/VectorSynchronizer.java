package com.company;

public class VectorSynchronizer {

    private Vector v;
    private volatile int current = 0;
    private Object lock = new Object();
    private boolean set = false;

    public VectorSynchronizer(Vector v) {
        this.v = v;
    }

    public double read() throws InterruptedException {
        double val;
        synchronized (lock) {
            if (!canRead()) {
                throw new InterruptedException();
            }
            while (!set) {
                lock.wait();
            }
            val = v.getElement(current++);
            System.out.println("Read: " + val);
            set = false;
            lock.notifyAll();
        }
        return val;
    }

    public void write(double val) throws InterruptedException {
        synchronized (lock) {
            if (!canWrite()) {
                throw new InterruptedException();
            }
            while (set) {
                lock.wait();
            }
            v.setElement(current, val);
            System.out.println("Write: " + val);
            set = true;
            lock.notifyAll();
        }
    }

    public boolean canRead() {
        return current < v.getSize();
    }

    public boolean canWrite() {
        return (!set && current < v.getSize()) || (set && current < v.getSize() - 1);
    }
}