package com.hp.test;

import java.util.Scanner;

/**
 * 请编写函数(或方法)fun，其功能是：将两个两位数的正整数 a、b 合
 * 并形成一个整数放在 c 中。合并的方式是：将 a 数的十位和个位数依次放在 c 数
 * 个位和十位上，b 数的十位和个位数依次放在 c 数的百位和千位上。
 * 例如，当 a=16、b=35 时，则 c=5361。
 * 其中，a、b 为函数(或方法)fun 的输入参数，c 为函数(或方法)fun 的返回值
 */
public class Test16Returned {

    public static void main(String[] args) {
        Scanner sca=new Scanner(System.in);
        System.out.println("请输入第一个数字：");
        int a=sca.nextInt();
        System.out.println("请输入第二个数字：");
        int b =sca.nextInt();
        fun(a,b);
    }
    public static void fun(int a,int b){
        int w=b/10%10;
        int c=b%10;
        int y=a/10%10;
        int h=a%10;
        int [] r={c,w,h,y};
        for (int t:r) {
            System.out.print(t);
        }
    }
}
