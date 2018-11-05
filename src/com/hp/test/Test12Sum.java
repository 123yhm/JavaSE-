package com.hp.test;

import java.util.Scanner;

/**
 * 计算算式 1+21+22+23+…+2n 的值。
 *
 * n 由键盘输入，且 2 ≤ n ≤10。
 */
public class Test12Sum {
    public static void main(String[] args) {
        Scanner sca=new Scanner(System.in);
        System.out.println("请输入2-10之间的数字：");
        int value=sca.nextInt();

        int a=1+21,sum=0;
        if (value>=2&&value<=10){

            for (int i = 1; i <=value ; i++) {
                sum+=a;
                a++;
            }
        }else{
            System.out.println("输入的值不在此范围内");
            return ;
        }
        System.out.println("总数为："+sum);
    }
}
