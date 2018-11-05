package com.hp.test.DDZ.src.com.java1823.ddz;

public class Action {

    // 准备
    public static final String GO = "#GO";
    // 叫地主
    public static final String BOSS = "#BOSS";
    // 取消 先出
    public static final String PASS = "#PASS";
    // 出牌
    public static final String OUT = "#OUT#";

    public static final String END = "#";

    // 判断用户的输入是否正确
    public static boolean judgeInput(String msg) {
        String value = msg.toUpperCase();
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
