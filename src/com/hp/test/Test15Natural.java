package com.hp.test;

import javafx.scene.transform.Scale;

import java.util.Scanner;

/**
 * 求 n 以内（不包括 n）同时能被 3 和 7 整除的所有自然数之和的平
 * 方根 s，然后将结果 s 输出。例如若 n 为 1000 时，则 s=153.909064。
 * 要求：使用循环语句结构实现。 ②n 由键盘输入，且 100 ≤ n ≤10000
 */
public class Test15Natural {
    public static void main(String[] args) {
        Scanner sca=new Scanner(System.in);
        System.out.println("请输入100到10000之间的任意数字");
        int result=sca.nextInt();
        int sum=0;
        if (result>=100&& result<=10000){
            for (int i = 0; i <result ; i++) {
                if (i%3==0&&i%7==0){
                    sum+=i;
                }
            }

        }
        System.out.println(sum+"的平方根："+Math.sqrt(sum));
    }
}
