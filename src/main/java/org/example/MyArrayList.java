package org.example;

import java.lang.annotation.ElementType;
import java.util.*;
import java.util.function.UnaryOperator;

/*
    * "Своя" реализация ArrayList
    * Включает в себя методы добавления элемента (по индексу, значению),
    * его удаления(по индексу, значению), получение элемента по индексу, полного очищения ArrayList
 */
public class MyArrayList<E> {

    /* Указываем размер листа "по-умолчанию" */
    private static final int DEFAULT_CAPACITY = 10;

    /* Создаём массив, где будут хратиться наши элементы*/
    private Object[] elementData;

    /* В этой переменной указывается реальное кол-во элементов в массиве */
    private int size = 0;

    /* КОНСТРУКТОРЫ */

    /* пустой конструктор создаёт массив на 10 элементов с приведением типа */
    public MyArrayList() {
        elementData = (E[]) new Object[DEFAULT_CAPACITY];
    }

    /* конструктор создаёт массив заданного размера с приведением типа */
    public MyArrayList (int initialCapacity) {
        elementData = (E[]) new Object[initialCapacity];
    }

    /*
        добавление элемента.
        Увеличиваем вместимость массива (при необходимости) и добавляем элемент в массив
     */
    public boolean add (E element) {
        ensureCapacity(size + 1);
        elementData[size++] = element;
        return true;
    }


    /*
        Добавление элемента по индексу
        Увеличиваем вместимость массива (при необходимости) и смещаем элементы вправо
        и добавляем элемент в массив
     */
    public boolean add(int index, E element) {
        ensureCapacity(size + 1);
        System.arraycopy(elementData,index, elementData, index+1, size-index);
        elementData[index] = element;
        size++;
        return true;
    }

    /*
        Проверка вместимости массива.
        Если при добавлении элемента в массиве нет места, то создаём массив большего размера
     */
    private void ensureCapacity(int minCapacity) {
        if (size < minCapacity) {
            int NewCapacity = (size * 3) / 2 + 1;
            E[] oldData = (E[]) elementData;
            elementData = (E[]) new Object[NewCapacity];
            System.arraycopy(oldData,0, elementData, 0,size);
        }
    }

    /*
        Получить элемент
        проверяем индекс, чтобы он был не больше размера массива и не был отрицательный
        если соответствует - даём элемент из массива по индексу,
        иначе - NULL
     */
    public E get (int index) {
        if((index < size) && (index >=0)) {
            return (E) elementData[index];
        }
        return null;
    }

    /*
        Удаление элемента по индексу
        1. Проверяем - не равен ли "0" размер массива. Если равен - false;
        2. Получаем кол-во элементов, которое будем копировать
        3. Копируем массив, сдвигая элементы, которые следуют после index, на одну позицию влево
        4. Сокращаем массив при помощи метода trimToSize()
     */
    public boolean remove (int index) {
        if(size == 0) {
            return false;
        }

        int numMoved = size - index - 1;
        System.arraycopy(elementData, index+1, elementData, index, numMoved);
        trimToSize();
        return true;
    }

    /*
        Удаление элемента по значению
        Проверяем, не равен ли размер массива нулю
        В цикле ищем соответствие. При нахождении определяет колв-о элементов,
        которые необходимо сдвинуть при удалении искомого элемента.
        Копируем массив без искомого элемента
        Сокращаем массив при помощи метода trimToSize()
     */
    public boolean remove(E element) {
        if(size == 0) {
            return false;
        }

        int numMoved = 0;
        int i;
        for (i = 0; i < size; i++) {
            if (elementData[i] == null && element == null) {
                break;
            }
            if ((elementData[i] != null) && (elementData[i].equals(element))) {
                numMoved = size - i - 1;
                break;
            }
        }

        System.arraycopy(elementData, i+1, elementData, i, numMoved);
        elementData[--size] = null;
        trimToSize();
        return true;

    }

    /*
        Данный метод уменьшает размер массива на один элемент.
        Удаляется последний
     */
    private void trimToSize() {
        if (size <= 0) {
            return;
        }
        size--;
        E[] newArray = (E[]) new Object[size];
        System.arraycopy(elementData,0,newArray,0, size);
    }

    /*
        Очистить коллекцию.
        При помощи цикла присваиваем всем элементам массива значение null.
        Размер массива равен "0".
     */
    public void clear() {
        for(int i = 0; i<size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }

    /*
    Метод, возвращающий размер массива
     */
    public int size() {
        return size;
    }

    public void sort() {
        sort(elementData, 0, size-1);
    }

    private void sort(Object[] elementData, int leftBorder, int rightBorder) {
        if(size==0) {
            return;
        }

        if(elementData[0] instanceof Integer) {
            int leftMarker = leftBorder;
            int rightMarker = rightBorder;
            int pivot = (int) elementData[(leftMarker+rightMarker) / 2];

            do {
                while ((int)elementData[leftMarker] < pivot) {
                    leftMarker++;
                }

                while((int) elementData[rightMarker] > pivot) {
                    rightMarker--;
                }

                if(leftMarker <= rightMarker) {
                    if(leftMarker < rightMarker) {
                        int tmp = (int) elementData[leftMarker];
                        elementData[leftMarker] = elementData[rightMarker];
                        elementData[rightMarker] = tmp;
                    }
                    leftMarker++;
                    rightMarker--;
                }
            } while (leftMarker <= rightMarker);

            if(leftMarker < rightBorder) {
                sort(elementData,leftMarker, rightBorder);
            }
            if(leftBorder < rightMarker) {
                sort(elementData, leftBorder, rightMarker);
            }
        }
    }
}

