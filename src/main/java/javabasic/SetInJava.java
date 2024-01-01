package javabasic;

import java.util.HashSet;
import java.util.Set;

public class SetInJava {
    public static void main(String[] args) {
        // Set la 1 collection cac phan tu khong co thu tu va khong trung lap
        Set<String> mySet = new HashSet<>();

        // Thêm phần tử vào tập hợp
        mySet.add("Apple");
        mySet.add("Banana");
        mySet.add("Orange");

        // Truy cập và in ra các phần tử của tập hợp
        System.out.println("Elements in the set:");
        for (String element : mySet) {
            System.out.println(element);
        }

        // Kiểm tra sự tồn tại của một phần tử
        if (mySet.contains("Banana")) {
            System.out.println("Banana is in the set.");
        }

        // Lấy kích thước của tập hợp
        int size = mySet.size();
        System.out.println("Size of the set: " + size);

        // Xóa một phần tử
        mySet.remove("Orange");

        // In ra tập hợp sau khi xóa
        System.out.println("Elements in the set after removal:");
        for (String element : mySet) {
            System.out.println(element);
        }
    }
}
