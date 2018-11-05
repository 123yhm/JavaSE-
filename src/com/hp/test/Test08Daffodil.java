package com.hp.test;

import java.util.Scanner;

/**
 * 编程实现判断一个整数是否为“水仙花数”。所谓“水仙花数”是指一
 * 个三位的整数，其各位数字立方和等于该数本身。例如：153 是一个“水仙花数”，
 * 因为 153=13＋53＋33。
 */
public class Test08Daffodil {
    public static void main(String[] args) {
        Scanner sca=new Scanner(System.in);
        System.out.println("请输入一个三位数：");
        int i = sca.nextInt();
        if(i>99&&i<1000){
            int a=i/100;//百位数
            int b=i/10%10;//十位数
            int c=i%10;
            System.out.println("a:"+a+"b:"+b+"c:"+c);
            int result = (int) Math.pow(a, 3) + (int) Math.pow(b, 3) + (int) Math.pow(c, 3);
            if(result==i){
                System.out.println(result+"是水仙花数");
            }else{
                System.out.println("该数不为水仙花数");
            }
        }else{
            System.out.println("输入有误，请重新输入--谢谢");
        }
    }
}
