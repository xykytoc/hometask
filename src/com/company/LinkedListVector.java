package com.company;

import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;


        public class LinkedListVector implements Vector, Serializable, Cloneable {

        private transient VectorType type = VectorType.LinkedList;

        //Ссылка на голову связного списка
        private Node head = new Node();

        //Текущая длина связного списка
        private int size = 0;

        //Ссылка на последний использовавшийся элемент связного списка
        private transient Node current = head;

        //Номер последнего использовавшегося элемента связного списка. Значение "-1" соответствует голове.
        private transient int currentIndex = -1;
        private transient int modCount = 0;

        public LinkedListVector() {
            head.prev = head;
            head.next = head;
        }


        //Значения списка генерируются случайно
        public LinkedListVector(int count) {
            this();
            for (int i = 0; i < count; i++) {
                Random xd = new Random();
                addElement(Math.ceil(xd.nextDouble() * 25));
            }
        }

        //Метод доступа к элементу списка.
        private Node gotoNumber(int index) throws VectorIndexOutOfBoundsException {
            if ((index >= 0) && (index < size)) {
                if (index < currentIndex) {
                    if (index < currentIndex - index) {
                        current = head;
                        for (int i = -1; i < index; i++) {
                            current = current.next;
                        }
                    } else {
                        for (int i = currentIndex; i > index; i--) {
                            current = current.prev;
                        }
                    }
                } else {
                    if (index - currentIndex < size - index) {
                        for (int i = currentIndex; i < index; i++) {
                            current = current.next;
                        }
                    } else {
                        current = head;
                        for (int i = size; i > index; i--) {
                            current = current.prev;
                        }
                    }
                }
                currentIndex = index;
                return current;
            } else {
                throw new VectorIndexOutOfBoundsException();
            }
        }

        // Добавление элемент в конец списка
        public final void addElement(double value) {
            Node newnode = new Node(value);
            newnode.prev = head.prev;
            newnode.next = head;

            head.prev.next = newnode;
            head.prev = newnode;

            size++;
            modCount++;
        }

        // Удаление элемента по индексу
        public void removeElement(int index) throws VectorIndexOutOfBoundsException {
            Node element = gotoNumber(index);
            if (element != head) {
                currentIndex = -1;
                current = head;
                element.prev.next = element.next;
                element.next.prev = element.prev;
                element.next = null;
                element.prev = null;
                size--;
                modCount++;
            }
        }

        //Полуение типа вектора
        public VectorType getVectorType() {
            return type;
        }

        //Получение размера
        public int getSize() {
            return size;
        }

        //Получение значения элемента по индексу
        public double getElement(int index) throws VectorIndexOutOfBoundsException {
            return gotoNumber(index).value;
        }

        // Установка значения элемента по индексу
        public void setElement(int index, double value)
                throws VectorIndexOutOfBoundsException {
            Node element = gotoNumber(index);
            element.value = value;
        }

        public void print() {
            Node seek = head.next;
            while (seek != head) {
                System.out.print(seek.value);
                System.out.print(" ");
                seek = seek.next;
            }
            System.out.println();
        }

        @Override
        public String toString() {
            StringBuffer sb = new StringBuffer();
            sb = sb.append(this.size);
            sb = sb.append(' ');
            Node selector = head.next;
            while (selector != head) {
                sb = sb.append(selector.value);
                sb = sb.append(' ');
                selector = selector.next;
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
            Node selector = head.next;
            while (selector != head) {
                t = Double.doubleToLongBits(selector.value);
                result ^= (((int) (t >> 32)) ^ (int) (t & 0x00000000FFFFFFFF));
                selector = selector.next;
            }
            return result;
        }

        @Override
        public Object clone() {
            LinkedListVector vx = new LinkedListVector();
            Node selector = head.next;
            while (selector != head) {
                vx.addElement(selector.value);
                selector = selector.next;
            }
            return vx;
        }

        public Iterator iterator() {
            return new LinkedListVectorIterator();
        }

        private class Node implements Serializable {

            //Значение, которое хранит элемент связного списка
            double value = Double.NaN;

            //Ссылка на предыдущий элемент связного списка
            Node prev = null;

            //Ссылка на следующий элемент связного списка
            Node next = null;

            public Node(double val) {
                this.value = val;
            }

            public Node() {
            }
        }

        private class LinkedListVectorIterator implements Iterator {

            private int current = 0;
            private int lastRet = -1;
            int expectedModCount = modCount;

            public LinkedListVectorIterator() {
            }

            public void remove() {
                if (lastRet == -1) {
                    throw new IllegalStateException();
                }
                checkForComodification();

                try {
                    LinkedListVector.this.removeElement(lastRet);
                    if (lastRet < current) {
                        current--;
                    }
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
                    double next = LinkedListVector.this.getElement(current);
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




