package org.example;

import java.util.ArrayList;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        MyArrayList<String> myArrayList = new MyArrayList<>();
        for (int Numbers = 0; Numbers <=10; Numbers ++) {
            myArrayList.add(String.valueOf(Numbers));
        }

        showArray(myArrayList);

        myArrayList.remove(9);
        myArrayList.remove(8);

        showArray(myArrayList);

        myArrayList.remove("10");
        showArray(myArrayList);

        myArrayList.add("122");
        showArray(myArrayList);

        myArrayList.add(0,"1000");
        showArray(myArrayList);

    }

    static void showArray(MyArrayList<?> myArrayList) {
        for (int i = 0; i<myArrayList.size(); i++) {
            System.out.println(myArrayList.get(i));
        }

        System.out.println("==================");
    }
}
