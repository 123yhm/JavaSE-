package com.hp.test;

/**
 * 有一分数序列：2/1，3/2，5/3，8/5，13/8，21/13 … 求出这个数
 * 21
 * 列的前 20 项之和。
 * 要求：利用循环计算该数列的和。注意分子分母的变化规律
 */
public class Test11Sum {
    public static void main(String[] args) {
        int a1=1,a2=2,sum=0,b=0;
        for (int i = 0; i <20 ; i++) {
            a1=a2;
            a2=a1+a2;
            sum+=a2/a1;

        }//32.66
        System.out.println("结果为："+sum);

    }
}
