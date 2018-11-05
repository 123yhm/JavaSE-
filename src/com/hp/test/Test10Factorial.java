package com.hp.test;

/**
 * 求5的阶乘：
 * 1*2*3*4*5
 */
public class Test10Factorial {
    public static void main(String[] args) {
        int run1 = run(5);
        //Integer eat = eat(5);
        System.out.println(run1);
    }
    public static Integer run(int a){//使用阶乘的方式
        int sum=1;
        if(a==0){
            return 1;
        }

        return a*run(a-1);
    }
    public static Integer eat(int a){//未使用阶乘的方式
        int sum=1;
        if (a==0){
            return 1;
        }
        for (int i=1;i<a;i++){
            sum+=sum*i;
        }
        return sum;
    }
}
