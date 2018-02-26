package com.company;

import java.util.Iterator;

public interface Vector {

    VectorType getVectorType();                                                         //получение типа вектора
    int getSize();                                                                      //получение размера вектора
    double getElement(int index) throws VectorIndexOutOfBoundsException;                //добавление элемента в конец списка
    void setElement(int index, double value) throws VectorIndexOutOfBoundsException;    //удаление элемента по индексу
    void addElement(double value);                                                      //добавление элемента в конец списка
    void removeElement(int index);                                                      //удаление элемента по индексу
    Iterator iterator();                                                                //итератор
    void print();                                                                       //вывод списка на экран





}
