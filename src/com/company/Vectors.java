package com.company;

/*Создать отдельный класс Vectors, содержащий статические методы работы с векторами:
+        умножения вектора на скаляр,
+        сложения двух векторов
+        нахождения скалярного произведения двух векторов.*/

/*      - записи вектора в байтовый поток (использовать DataOutputStream)
public static void outputVector(Vector v, OutputStream out),
        - чтения вектора из байтового потока (использовать DataInputStream)
public static Vector inputVector(InputStream in),
        - записи вектора в символьный поток (использовать PrintWriter)
public static void writeVector(Vector v, Writer out),
        - чтения вектора из символьного потока (использовать BufferedReader или StreamTokenizer)
*/

import java.io.*;
import java.lang.reflect.Constructor;

public class Vectors {


    public static boolean useOutLock = false;
    private static VectorFactory factoryInstance = null;

    public static VectorFactory getFactory() {
        return factoryInstance;
    }

    public static void setFactory(VectorFactory factory) {
        factoryInstance = factory;
    }

    private static Vector createThisType(Vector vector) {
        switch (vector.getVectorType()) {
            case Array:
                return new ArrayVector();
            case LinkedList:
                return new LinkedListVector();
        }
        return null;
    }


    // Умножение вектора на число
    public static Vector mult(Vector vector, double value) {
        Vector res = createInstance(vector, 0);
        for (int i = 0; i < vector.getSize(); i++) {
            res.addElement(value * vector.getElement(i));
        }
        return res;
    }


    // Сложение векторов
    public static Vector sum(Vector one, Vector two)
            throws IncompatibleVectorSizesException {

        if (one.getSize() != two.getSize()) {
            throw new IncompatibleVectorSizesException();
        }

        Vector res = createInstance(one,0);
        for (int i = 0; i < one.getSize(); i++) {
            res.addElement(one.getElement(i) + two.getElement(i));
        }
        return res;
    }


    // Скалярное проивзведение векторов
    public static double scalarMult(Vector one, Vector two)
            throws IncompatibleVectorSizesException {

        if (one.getSize() != two.getSize()) {
            throw new IncompatibleVectorSizesException();
        }
        double res = 0;
        for (int i = 0; i < one.getSize(); i++) {
            res += one.getElement(i) * two.getElement(i);
        }
        return res;
    }

    // Вывод вектора в поток
    public static void outputVector(Vector v, OutputStream out)
            throws IOException {
        DataOutputStream writer = new DataOutputStream(out);
        writer.writeInt(v.getSize());
        for (int i = 0; i < v.getSize(); i++) {
            writer.writeDouble(v.getElement(i));
        }
        writer.flush();
    }


    // Ввод вектора из потока
    public static Vector inputVector(InputStream in) throws IOException {
        DataInputStream reader = new DataInputStream(in);
        Vector vector = createInstance(reader.readInt());
        for (int i = 0; i < vector.getSize(); i++) {
            vector.setElement(i, reader.readDouble());
        }
        return vector;
    }


    // Запись вектора в текстовый поток
    public static void writeVector(Vector v, Writer out) throws IOException {
        PrintWriter writer = new PrintWriter(out);
        writer.write(Integer.toString(v.getSize()));
        writer.write(' ');
        for (int i = 0; i < v.getSize(); i++) {
            writer.write(Double.toString(v.getElement(i)));
            writer.write(' ');
        }
        writer.flush();
    }

    // Чтение вектора из текстового потока
    public static Vector readVector(Reader in)
            throws IOException, IncorrectStreamContent {
        StreamTokenizer reader = new StreamTokenizer(in);
        Vector vector = null;
        try {
            if (reader.nextToken() == StreamTokenizer.TT_NUMBER) {
                int count = (int) reader.nval;
                vector = createInstance(count);
                for (int i = 0; i < count; i++) {
                    if (reader.nextToken() == StreamTokenizer.TT_EOF) {
                        throw new Exception("Some elements are missing");
                    }
                    vector.setElement(i, reader.nval);
                }
            } else {
                throw new Exception("Length of list isn't set");
            }
        } catch (Exception ex) {
            throw new IncorrectStreamContent(ex.getMessage());
        }
        return vector;
    }

    public static Vector createInstance(int size) {
        if (factoryInstance != null) {
            return factoryInstance.createInstance(size);
        }
        throw new NullPointerException();
    }

    public static Vector createInstance(Vector vector, int size) {
        try {
            Class cl = vector.getClass();
            Constructor constr = cl.getConstructor(int.class);
            return (Vector) constr.newInstance(size);
        } catch (Exception ex) {
            throw new RuntimeException("Couldn't create instance of Vector");
        }
    }
}