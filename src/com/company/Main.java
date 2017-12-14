package com.company;

//import java.lang.reflect.Array;

public class Main extends ArrayVector
{

    public static void main(String[] args)
    {
        ArrayVector array1 = new ArrayVector(); //создание вектора
        array1.append(8.3);
        array1.append(5.1);
        array1.append(3.5);
        array1.append(4.0); //заполнение вектора различными значениями
        array1.append(3.2);
        array1.append(9.4);
        array1.append(1.6);
        array1.append(4.2);
        array1.append(11.11);

        System.out.println("Вектор ");
        for (int i = 0; i < array1.getSize(); i++) //вывод каждого элемента вектора
        {
            System.out.println(array1.getElement(i));
        }

        System.out.println(" ");
        System.out.println("Длина: ");
        System.out.println(array1.getSize());    //вызов метода getSize из класса ArrayVector
        System.out.println(" ");
        array1.getElement(2); //вызов метода getElement из класса ArrayVector по индексу
        System.out.println(" ");
        System.out.println("Максимальное значение: "); //вывод строки
        System.out.println(array1.getMax()); //вывод результата метода getMax
        System.out.println("Минимальное значение: "); //вывод строки
        System.out.println(array1.getMin()); //вывод результата метода getMin
        System.out.println("Среднее значение:"); //вывод строки
        System.out.println(array1.getAverage()); //вывод результата метода getAverage
        System.out.println(" ");
        //System.out.println(ArrayVector.mult());
        array1.getNorm(); //вызов метода getNorm (с выводом результата)
        System.out.println(" ");
        array1.sort();


        System.out.println(" ");

        System.out.println("***Сортировка***");
        for (int i = 0; i < array1.getSize(); i++) //вывод каждого элемента отсортированного вектора
        {
            System.out.println(array1.getElement(i));
        }

        System.out.println(" ");
        System.out.println(" ");

        ArrayVector rew = ArrayVector.mult(array1, 25);
        System.out.println("Умножение на 25: ");
        for (int i = 0; i < rew.getSize(); i++) //вывод каждого умноженного элемента вектора
        {
            System.out.println(rew.getElement(i));
        }
        System.out.println(" ");

        ArrayVector som = ArrayVector.sum(array1, rew); //создание вектора путем сложения двух
        System.out.println("Сложение векторов:");
        for (int i = 0; i < som.getSize(); i++)
        {
            System.out.println(som.getElement(i));
        }

        System.out.println(" ");
        System.out.println("Скалярное произведение векторов:");
        System.out.println(ArrayVector.scalarMult(array1,rew));
    }
}