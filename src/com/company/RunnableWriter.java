package com.company;

import java.util.Random;

public class RunnableWriter implements Runnable{

    private VectorSynchronizer vectorSync = null;

    public RunnableWriter(VectorSynchronizer vectorSync){
        this.vectorSync = vectorSync;
    }

    public void run(){
        Random rand = new Random();
        if (vectorSync != null) {
            while (vectorSync.canWrite()){
                try {
                    double value = rand.nextDouble() * 100;
                    vectorSync.write(value);
                } catch (Exception ex) {
                    System.out.println("ОШИБКА");
                }
            }
        }
    }
}