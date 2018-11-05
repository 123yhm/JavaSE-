package com.hp.test;

import java.util.Random;

public class CallName1823 {


    static String[] NAMES = {
            "周文杰",
            "陈超",
            "刘阳",
            "冯靖",
            "李兵",
            "喻文健",
            "曾彪",
            "阳浩明",
            "杨世芳",
            "曾灿",
            "向国城",
            "汪茂粮",
            "李梦辉",
            "何海涛",
            "赵佳",
            "李俊杰",
            "彭正佳",
            "尹阳杰",
            "吕海涛",
            "唐鹏",
            "李小虎",
            "雷雨",
            "周虎玺",
            "伍涛",
            "伍扬",
            "冯嘉",
            "张智豪",
            "杨思琪",
            "何明聪",
            "文磊梁",
            "谢浩源",
            "李强",
            "陈祥"
    };


    public static void main(String[] args) {

        Random random = new Random();

        int index = random.nextInt(NAMES.length);
        System.out.println("请" + NAMES[index] + "回答问题");

    }


}
