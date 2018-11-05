package com.hp.test;

import java.util.Arrays;

/**
 * 已知字符串数组 A，包含初始数据：a1,a2,a3,a4,a5；字符串数组 B，
 * 包含初始数据：b1,b2,b3,b4,b5。编写程序将数组 A、B 的每一对应数据项相连接，
 * 然后存入字符串数组 C，并输出数组 C。输出结果为：a1b1,a2b2,a3b3,a4b4,a5b5
 */
public class Test09ArrayJoint {
    public static void main(String[] args) {

        String[] a={"a1","a2","a3","a4","a5"};
        String[] b={"b1","b2","b3","b4","b5"};
        int length = a.length;//得到公共的长度
        String[] c= new  String[length];
        for (int i=0;i<length;i++){//我要循环几次
            c[i]=a[i]+b[i];//拼接数组中的字符串，使用共同的顺序
        }
        for (String hm:c) {
            System.out.println(hm);
        }
    }
}
