package com.company;

import java.util.Iterator;
import java.lang.reflect.Method;
import java.lang.Thread.State;
import java.util.concurrent.TimeUnit;

public class Main
{

    public static void main(String[] args) {

            try {
                // 1st Task
                System.out.println(" ");
                System.out.println("****** 1st Task ******");
                ArrayVector vx = new ArrayVector(25);
                WriterThread writer = new WriterThread(vx);
                ReaderThread reader = new ReaderThread(vx);
                writer.start();
                reader.start();
                while ((reader.getState() != State.TERMINATED)
                        && (writer.getState() != State.TERMINATED)) {
                    TimeUnit.MILLISECONDS.sleep(100);
                }

                // 2nd task
                System.out.println(" ");
                System.out.println("****** 2nd Task ******");
                vx = new ArrayVector(25);
                VectorSynchronizer vxSync = new VectorSynchronizer(vx);

                new Thread(new RunnableReader(vxSync)).start();
                new Thread(new RunnableWriter(vxSync)).start();

                while (vxSync.canRead() || vxSync.canWrite()) {
                    TimeUnit.MILLISECONDS.sleep(100);
                }

                // 3rd Task
                System.out.println(" ");
                System.out.println("****** 3rd Task ******");
                Thread printer = new Thread(new SafePrinter());
                Thread printer1 = new Thread(new SafePrinter());
                Thread printer2 = new Thread(new SafePrinter());

                Vectors.useOutLock = true;

                printer.start();
                printer1.start();
                printer2.start();

            } catch (Exception ex) {
                System.out.println("ERROR");
            }
        }
}
