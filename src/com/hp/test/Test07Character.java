package com.hp.test;

import org.junit.Test;

import java.util.Scanner;

/**
 * 输入两个正整数 m 和 n，输出其最大公约数和最小公倍数
 * m=10，7
 * n=9,6
 * 公约数：它是一个能被若干个整数同时均整除的整数--1 2 5 10---1 3 9---1
 * 公倍数：在两个或两个以上的自然数中，如果它们有相同的倍数，这些倍数就是它们的公倍数。1*2*5--1*3*3|=1*2*3*5*3=90
 * 1*7
 * 1*2*3||1*7*2*3=42
 */
public class Test07Character {
    public static void main(String[] args) {
        System.out.println("请输入第一个数：");
        int min=0;
        Scanner sca=new Scanner(System.in);
        int a1=sca.nextInt();
        System.out.println("请输入第二个数：");
        int a2=sca.nextInt();
        int aa=a1>a2?a1:a2;
        a:for (int i=1;i<=a1*a2;i++){
            if(i%a1==0&&i%a2==0){
                System.out.println("最小公倍数："+i);
                break a;
            }
        }
    }
    @Test
    public void test(){
        System.out.println(7%7==0);
    }
}
