package com.hp.test;

/**
 * 某班同学上体育课，从 1 开始报数，共 38 人，老师要求按 1、2、
 * 3 重复报数，报数为 1 的同学往前走一步，而报数为 2 的同学往后退一步，试分
 * 别将往前走一步和往后退一步的同学的序号打印出来
 *
 * 38人
 * 1:前
 * 2：后
 *
 */
public class Test06Physical {
    public static void main(String[] args) {
        for (int i=0;i<=38;i++){
            if (i%3==1){
                System.out.println("前"+i);
            }
            if(i%3==2)
                System.out.println("后："+i);
            }

        }

}
