package com.hp.test;

/**
 *孙悟空在大闹蟠桃园的时候，第一天吃掉了所有桃子总数一半多一
 * 个，第二天又将剩下的桃子吃掉一半多一个，以后每天吃掉前一天剩下的一半多
 * 一个，到第 n 天准备吃的时候只剩下一个桃子。这下可把神仙们心疼坏了，请帮
 * 忙计算一下，第一天开始吃的时候桃园一共有多少个桃子。
 */

import java.util.Scanner;

/**
 * ①使用循环结构语句实现。②整数 N 由键盘输入，且 2 ≤ N ≤10。
 */
public class Test03Monkey {
    public static void main(String[] args) {
        int sum=1;
        Scanner sca=new Scanner(System.in);
        System.out.println("请输入天数：");
        int input = sca.nextInt();
        if(input>=2 &&input<=10){
            for (int i=2;i<=input;i++){
                sum=(sum+1)*2;
            }
        }else{
            System.out.println("输入内容不在此题要求以内，请重新输入：");
            return;
        }
        System.out.println("第一天蟠桃的个数为："+sum);
    }
}
