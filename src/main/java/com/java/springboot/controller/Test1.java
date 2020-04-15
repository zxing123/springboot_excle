package com.java.springboot.controller;

import org.junit.Test;

/**
 * 冒泡排序和选择排序
 */

public class Test1 {
    @Test
    public  void test1() {
        int [] arr =  {155,2,1,10,22,55,88,89,5,9,5,8,6};
        for (int i = 0; i < arr.length - 1; i++) {				//外循环只需要比较arr.length-1次就可以了
            for (int j = 0; j < arr.length -1- i; j++) {		//-1为了防止索引越界,-i为了提高效率
                if(arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j+1] = temp;
                }
            }
        }
        for(int i:arr){
            System.out.print(i+"\t");
        }
    }
    @Test
    public  void test2() {
        int [] arr =  {155,2,1,10,22,55,88,89,5,9,5,8,6};
        for (int i = 0; i < arr.length; i++) {				//外循环只需要比较arr.length-1次就可以了
            for (int j = i+1; j < arr.length; j++) {		//-1为了防止索引越界,-i为了提高效率
                if(arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        for(int i:arr){
            System.out.print(i+"\t");
        }
    }
}
