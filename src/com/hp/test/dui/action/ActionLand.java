package com.hp.test.dui.action;

public class ActionLand {
    // 准备
    public static final String GO = "START";
    // 谁先出
    public static final String BOSS = "#BOSS";
    // 不抢
    public static final String PASS = "#PASS";
    // 出牌
    public static final String OUT = "#";
    //结束
    public static final String END = "@";

    // 判断用户的输入是否正确

    public static boolean judgeInput(String msg) {
        String value = msg.toUpperCase();
        System.out.println(value);
        System.out.println("-------"+value.startsWith(OUT));
        if (value.equals(GO)) { // 表示准备
            return true;
        }
        if (value.equals(BOSS)) {
            return true;
        }
        if (value.equals(PASS)) {
            return true;
        }
        if (value.startsWith(OUT) && value.endsWith(END)) {
            return true;
        }
        return false;
    }
    // 判断出牌是否合理
    public static boolean judgeOut(String value) {
        if (value.startsWith(OUT) && value.endsWith(END)) {
            return true;
        }
        return false;
    }
}
