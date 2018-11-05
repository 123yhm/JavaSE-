package com.hp.test;

/**
 * ：一个人很倒霉，不小心打碎了一位妇女的一篮子鸡蛋。为了赔偿便
 * 询问篮子里有多少鸡蛋。那妇女说，她也不清楚，只记得每次拿两个则剩一个，
 * 每次拿 3 个则剩 2 个，每次拿 5 个则剩 4 个，若每个鸡蛋 1 元，请你帮忙编程，
 * 计算最少应赔多少钱？
 *
 * 3x+2
 * 3x+3
 * 3x+5
 *
 */
public class Test05Egg {
    public static void main(String[] args) {
        for (int i = 0;; i++) {
            if (i%2==1&&i%3==2&&i%5==4){
                System.out.println("鸡蛋的总数为："+i);
                break;
            }
        }
    }
}
