package com.company;

public class RunnableReader implements Runnable{

    private VectorSynchronizer vectorSync = null;

    public RunnableReader(VectorSynchronizer vectorSync){
        this.vectorSync = vectorSync;
    }

    public void run(){
        if (vectorSync != null) {
            while (vectorSync.canRead()){
                try {
                    vectorSync.read();
                } catch (Exception ex) {
                    System.out.println("ERROR");
                }
            }
        }
    }
}
