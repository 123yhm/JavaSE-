package com.hp.test;

import java.util.*;

/**
 * ：编写一个程序，对用户输入的任意一组字符如{3，1，4，7，2，1，
 * 1，2，2}，输出其中出现次数最多的字符，并显示其出现次数。如果有多个字符
 * 出现次数均为最大且相等，则输出最先出现的那个字符和它出现的次数。例如，
 * 上面输入的字符集合中，“1”和“2”都出现了 3 次，均为最大出现次数，因为
 * “1”先出现，则输出字符“1”和它出现的次数 3 次。
 * 要求：使用分支、循环结构语句实现
 */
public class Test14NumberTime {


    public static void main(String[] args) {
        Scanner sca = new Scanner(System.in);
        System.out.println("请输入1-5个数字：");
        int[] number = new int[5];
        for (int i = 0; i < number.length; i++) {

            number[i] = sca.nextInt();

        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int index = 1, eque = 1;
        int hm = 0;
        for (int i = 0; i < number.length; i++) {
            System.out.println(">>>" + number[i]);
            if (number[i] != hm) {
                map.put(number[i], index++);
                hm = number[i];
                System.out.println("--" + hm);
                eque = 1;
            }
            index = 1;
            if (number[i] == hm) {
                map.put(number[i], eque++);
                hm = number[i];
            }
        }
        Set keys = map.entrySet();
        Iterator iterator = keys.iterator();
        while (iterator.hasNext()) {
            Map.Entry me = (Map.Entry) iterator.next();
            System.out.println(me.getKey() + "===" + me.getValue());
        }

    }
}
