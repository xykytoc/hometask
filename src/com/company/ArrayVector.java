package com.company;

import java.lang.Math;
import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;



public class ArrayVector implements Vector, Serializable, Cloneable
 {
     private double[] abc;
     private int length;
     private transient VectorType type = VectorType.Array;
     protected transient int modCount = 0;

     public ArrayVector()
     {
         abc = new double[0];
         length = 0;

     }


     public ArrayVector(int length)
     {
         abc = new double[length];
         for (int i = 0; i < length; i++)
             abc[i] = 0;
         this.length = length;
     }

     public int getCapacity() {
         return abc.length;
     }

     public int find(double value) {
         int i = 0;
         while ((i < length) && (abc[i] != value)) {
             i++;
         }
         if (i < length) {
             return i;
         } else {
             return -1;
         }
     }


     public double getNorm() {
         double norm = 0;
         for (int i = 0; i < length; i++) {
             norm += abc[i] * abc[i];
         }
         norm = Math.sqrt(norm);
         return norm;
     }

     int getModification() {
         return modCount;
     }


     public VectorType getVectorType()
     { return type;}
     

     public void sort() {
         boolean sorted = false;
         double tmp = 0;
         while (!sorted) {
             sorted = true;
             for (int i = 0; i < length - 1; i++) {
                 if (abc[i] < abc[i + 1]) {
                     sorted = false;
                     tmp = abc[i];
                     abc[i] = abc[i + 1];
                     abc[i + 1] = tmp;
                 }
             }
         }
         modCount++;
     }


     public int getSize()
     {
         return length;
     }


     public double getElement(int index) {
         if ((index < 0) || (index >= length)) {
             throw new VectorIndexOutOfBoundsException();
         }
         return abc[index];
     }

     public void setElement(int index, double value) {
         if ((index < 0) || (index >= length)) {
             throw new VectorIndexOutOfBoundsException();
         }
         abc[index] = value;
     }


     // Добавление элемента в конец
     public void addElement(double value) {
         length++;
         if (abc.length - length == -1) {
             double[] tmp = abc;
             abc = new double[length * 2];
             System.arraycopy(tmp, 0, abc, 0, length - 1);
         }
         abc[length - 1] = value;
         modCount++;
     }

     // Удаление элемента
     public void removeElement(int index) {
         if (index < 0) {
             throw new VectorIndexOutOfBoundsException();
         }
         if (length > 0) {
             length--;
             double[] newArray = new double[length];
             int cursor = 0;
             for (int i = 0; i < length; i++) {
                 if (index != i) {
                     newArray[cursor] = abc[i];
                     cursor++;
                 }
             }
             abc = newArray;
         } else {
             throw new IllegalStateException();
         }
         modCount++;
     }

     public void print() {
         for (int i = 0; i < length; i++) {
             System.out.print(abc[i]);
             System.out.print(" ");
         }
         System.out.println();
     }

     @Override
     public String toString() {
         StringBuffer sb = new StringBuffer();
         sb = sb.append(this.length);
         sb = sb.append(' ');
         for (int i = 0; i < length; i++) {
             sb = sb.append(abc[i]);
             sb = sb.append(' ');
         }
         return sb.toString();
     }

     @Override
     public boolean equals(Object obj) {
         boolean result = false;
         if (obj instanceof Vector) {
             Vector vx = (Vector) obj;
             if (vx.getSize() == getSize()) {
                 int i = 0;
                 while ((i < getSize())
                         && (getElement(i) == vx.getElement(i))) {
                     i++;
                 }
                 if (i == getSize()) {
                     result = true;
                 }
             }
         }
         return result;
     }

     @Override
     public int hashCode() {
         int result = 0;
         long t;
         for (int i = 0; i < abc.length; i++) {
             t = Double.doubleToLongBits(abc[i]);
             result ^= (((int) (t >> 32)) ^ (int) (t & 0x00000000FFFFFFFF));
         }
         return result;
     }

     @Override
     public Object clone() {
         ArrayVector vx = new ArrayVector(length);
         System.arraycopy(abc, 0, vx.abc, 0, length);
         return vx;
     }

     public Iterator iterator() {
         return new ArrayVectorIterator();
     }

     //Итератор для ArrayVector
     private class ArrayVectorIterator implements Iterator {
         private int current = 0;
         private int lastRet = -1;
         int expectedModCount = modCount;

         public ArrayVectorIterator() {
         }

         public void remove() {
             if (lastRet == -1)
                 throw new IllegalStateException();
             checkForComodification();

             try {
                 ArrayVector.this.removeElement(lastRet);
                 if (lastRet < current)
                     current--;
                 lastRet = -1;
                 expectedModCount = modCount;
             } catch (IndexOutOfBoundsException e) {
                 throw new ConcurrentModificationException();
             }
         }

         public boolean hasNext() {
             return current != getSize();
         }

         public Object next() {
             try {
                 double next = ArrayVector.this.getElement(current);
                 lastRet = current++;
                 return next;
             } catch (IndexOutOfBoundsException e) {
                 throw new NoSuchElementException();
             }
         }

         final void checkForComodification() {
             if (modCount != expectedModCount) {
                 throw new ConcurrentModificationException();
             }
         }
     }
 }
