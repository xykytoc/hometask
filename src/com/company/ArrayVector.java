package com.company;

import java.lang.Math;

 public class ArrayVector
 {
     private double[] abc;
     //int[] abc = {3, 7, 23, 9, 61, 5, 44, 1, 58, 15, 32, 79, 3, 47, 3};
     private int length;


     public ArrayVector()
     {
         abc = new double[15];
         length = 0;
     }

     public ArrayVector(int length)
     {
         abc = new double[length];
         for (int i = 0; i < length; i++)
             abc[i] = 0;
         this.length = length;
     }


     public int getSize()
     {
         return length;
     }

     public void append(double val)
     {
         length++;
         if (abc.length - length == -1)
         {
             double[] t = abc;
             abc = new double[length * 2];
             for (int i = 0; i < length - 1; i++)
                 abc[i] = t[i];
         }
         abc[length - 1] = val;
     }


     public double getMax()
     {
         double max = abc[0];
         for (int i = 1; i < length; i++)
         {
             if (abc[i] > max)
                 max = abc[i];
         }
         return max;
     }

     public double getMin()
     {
         double min = abc[0];
         for (int i = 1; i < length; i++)
         {
             if (abc[i] < min)
                 min = abc[i];
         }
         return min;
     }

     public void getNorm()
     {
         double summ = 0;
         double norm;
         for (int i = 0; i < abc.length; i++)
         {
             summ += abc[i] * abc[i];

         }
         norm = Math.sqrt(summ);
         System.out.println("Евклидова норма: " + norm);
         return;
     }

     public double getAverage()
     {
         double sum = 0;
         for (int i = 0; i < length; i++)
             sum += abc[i];
         if (length != 0)
             return sum / length;
         else return 0;
     }


     public double getElement(int index)
     {
         return abc[index];
     }

     public void setElement(int index, double value)
     {
         abc[index] = value;
         return;
     }

     public void sort()
     {
         boolean sorted = false;
         double t = 0;
         while (!sorted)
         {
             sorted = true;
             for (int i = 0; i < length - 1; i++)
                 if (abc[i] < abc[i + 1])
                 {
                     sorted = false;

                     t = abc[i];
                     abc[i] = abc[i + 1];
                     abc[i + 1] = t;
                 }
         }
     }

     public static ArrayVector sum(ArrayVector first, ArrayVector second)
     {
         int len = first.length;
         if (first.length < second.length)
             len = second.length;
         ArrayVector result = new ArrayVector(len);
         for (int i = 0; i < first.length; i++)
             result.abc[i] = first.abc[i];
         for (int i = 0; i < second.length; i++)
             result.abc[i] += second.abc[i];
         return result;
     }


     public static ArrayVector mult(ArrayVector cde, double val)
     {
         ArrayVector mul = new ArrayVector();
         for (int i = 0; i < cde.length; i++)
             mul.append(val * cde.abc[i]);
         //System.out.println("hey ho "+mul);
         return mul;
     }

     public int find(double value)
     {
         int i = 0;
         while ((i < length) && (abc[i] != value))
             i++;
         if (i < length)
             return i;
         else
             return -1;
     }

     public static double scalarMult(ArrayVector first, ArrayVector second)
     {
         double result = 0;
         int i = 0;
         if (first.length == second.length) {
             for (i=0;i < first.length; i++);
             result += first.abc[i] * second.abc[i];
         }
         return result;
     }
 }