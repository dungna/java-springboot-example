package javabasic;

import java.util.ArrayList;
import java.util.List;

public class ListInJava {
    public static void main(String[] args) {
        // List la 1 collection chua cac phan tu co the trung lap va co thu tu
        List<String> myList = new ArrayList<>();

        // Them phan tu vao List
        myList.add("Hello");
        myList.add("Hi");
        myList.add("Xin chao");

        // In cac phan tu trong List
        for (String i: myList) {
            System.out.println(i);
        }
    }
}
