package ru.otus;


import com.google.common.base.MoreObjects;

public class HelloOtus {
    public static void main(String[] args) {
        String str1 = null;
        String str2 = "Hello OTUS!";
        System.out.println(MoreObjects.firstNonNull(str1, str2));
    }
}