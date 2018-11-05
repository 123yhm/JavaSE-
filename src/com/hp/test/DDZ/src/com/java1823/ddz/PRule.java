package com.hp.test.DDZ.src.com.java1823.ddz;


import java.util.ArrayList;
import java.util.List;

/**
 * 定义牌的规则
 */
public class PRule {

    // 判断牌的规则
    public static PType judgeP(List<P> list) {
        int size = list.size();
        PType result = null;
        switch (size) {
            case 1:
                result = new PType(1, PType.TYPE_DANG, list.get(0));
                break;
            case 2:
                result = isTwo(list);
                break;
            case 3:
                result = isThr(list);
                break;
            case 4:
                result = isFor(list);
                break;
            case 5:
                result = isFive(list);
                break;
            default:
                result = isSix(list);
                break;
        }
        return result;
    }

    private static PType isSix(List<P> list) {
        PUtil.sortP(list);
        PType p = isShunzi(list);
        if (p != null) {
            return p;
        }
        p = isLiandui(list);
        if (p != null) {
            return p;
        }
        p = isFourAndTwo(list);
        if (p != null) {
            return p;
        }
        p = isPlan(list);
        if (p != null) {
            return p;
        }
        return null;
    }

    // 判断牌堆中有没有出现四张一样的牌
    private static boolean isIncludeFor(List<P> list) {
        PUtil.sortP(list);
        // 如果牌中出现四张一样的牌，返回false
        int cnt = 1; // 用来统计相同牌的数量
        P prev = list.get(0); // 表示第一张牌
        for (int i = 1; i < list.size(); i++) {
            P tem = list.get(i);
            if (tem.getNumber() == prev.getNumber()) {
                cnt++;
            } else {
                prev = tem;
                cnt = 1;
            }
            if (cnt == 4) {
                return true;
            }
        }
        return false;
    }


    // 判断飞机
    private static PType isPlan(List<P> list) {
        if (list.size() < 6) {
            return null;
        }
        PUtil.sortP(list);
        P p1 = list.get(list.size() - 2); // 倒数第二张是小王的话，说明这牌中有王炸，不合格
        if (p1.getNumber() == 13) {
            return null;
        }
        if (isIncludeFor(list)) {
            return null;
        }
        List<P> temList = new ArrayList<>();

        int cnt = 1; // 用来统计相同牌的数量
        P prev = list.get(0); // 表示第一张牌
        for (int i = 1; i < list.size(); i++) {
            P tp = list.get(i);
            if (tp.getNumber() == prev.getNumber()) {
                cnt++;
            } else {
                prev = tp;
                cnt = 1;
            }
            if (cnt == 3 && prev.getNumber() != 12) { // 飞机中的连续三张 不存在2
                temList.add(prev);
            }
        }
        if (temList.size() < 2) {
            return null;
        }
        PType p = isShunzi(temList);
        if (p == null) {
            return null;
        }
        if (temList.size() * 3 == list.size()) { // 飞机没有带牌
            return new PType(list.size(), PType.TYPE_FEIJI, temList.get(temList.size() - 1));
        }
        if ((temList.size() * 3 + temList.size()) == list.size()) { // 飞机三带一
            return new PType(list.size(), PType.TYPE_FEIJI, temList.get(temList.size() - 1));
        }
        // 三带对的情况
        String value = appendValue(list); // 将牌转换成连续的(string) aaabbcc
        String newValue = value.replaceAll("([0-9a-c])\\1{2}", "");
        newValue = newValue.replaceAll("([0-9a-c])\\1", "$1");
        int newLen = newValue.length();
        if (newValue.length() * 5 == value.length()) {
            return new PType(list.size(), PType.TYPE_FEIJI, temList.get(temList.size() - 1));
        }
        return null;
    }


    // 四带二
    private static PType isFourAndTwo(List<P> list) {
        if (list.size() != 6) {
            return null;
        }
        PUtil.sortP(list);
        // 13 小王 14 大王
        P p1 = list.get(4);
        P p2 = list.get(5);
        if (p1.getNumber() == 13 && p2.getNumber() == 14) {
            return null;
        }
        String value = appendValue(list); // 将牌转换成连续的(string) aaabbcc
        String regex = "([0-9a-c])\\1{3}";
        String s = value.replaceAll(regex, "");
        if (s.length() == 2) {
            return new PType(list.size(), PType.TYPE_SHIDE, list.get(2));
        }
        return null;
    }


    private static PType isFive(List<P> list) {
        // 顺子  三带一对
        PUtil.sortP(list);
        PType p = isShunzi(list);
        if (p != null) {
            return p;
        }
        // 如果包含大小王，则不是三带一对
        for (P p2 : list) {
            if (p2.getNumber() >= 13) {
                return null;
            }
        }
        // 判断三带一对
        String value = appendValue(list);
        String aaabb = "([0-9a-c])\\1{2}([0-9a-c])\\2{1}";  // AAABB 形式一
        String aabbb = "([0-9a-c])\\1{1}([0-9a-c])\\2{2}";  // AABBB 形式二
        if (value.matches(aaabb) || value.matches(aabbb)) {
            return new PType(5, PType.TYPE_SHAND, list.get(2));
        }
        return null;
    }

    // 是否是连对
    private static PType isLiandui(List<P> list) {
        if (list.size() < 6 || list.size() % 2 != 0) {
            return null;
        }
        PUtil.sortP(list);
        P p1 = null;
        P p2 = null;
        for (int i = 0; i < list.size(); i += 2) {
            P tp1 = list.get(i);
            P tp2 = list.get(i + 1);
            if (tp1.getNumber() != tp2.getNumber() || tp1.getNumber() >= 12) {  // 大于等于12是判断 2 王的情况，他们不能出现在连对中
                return null;
            }
            if (p1 != null && p2 != null) {
                if (tp1.getNumber() - p1.getNumber() != 1) {
                    return null;
                }
            }
            p1 = tp1;
            p2 = tp2;
        }
        return new PType(list.size(), PType.TYPE_LIANDUI, list.get(list.size() - 1));
    }

    // 判断顺子的方法
    private static PType isShunzi(List<P> list) {
        PUtil.sortP(list);
        // 判断2  判断鬼
        for (P p : list) {
            if (p.getNumber() >= 12) {
                return null;
            }
        }
        P fir = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            P tem = list.get(i);
            if (tem.getNumber() - fir.getNumber() != 1) {
                return null;
            }
            fir = tem;
        }
        return new PType(list.size(), PType.TYPE_SHUNZI, list.get(list.size() - 1));
    }

    // 拼接扑克牌的值
    private static String appendValue(List<P> list) {
        String value = "";
        for (P p : list) {
            value += Integer.toHexString(p.getNumber());
        }
        return value;
    }


    // 判断四张的规则  炸弹  三带一
    private static PType isFor(List<P> list) {
        PUtil.sortP(list);
        String value = appendValue(list);
        if (value.matches("([0-9a-c])\\1{3}")) {  // 炸弹的正则
            return new PType(4, PType.TYPE_ZHADAN, list.get(0));
        }
        if (value.matches("[0-9a-c]*([0-9a-c])\\1{2}[0-9a-c]*")) {   // 三带一的正则
            return new PType(4, PType.TYPE_SHAND, list.get(1));
        }
        return null;
    }

    /**
     * 判断牌是否是 三张一样的
     */
    private static PType isThr(List<P> list) {
        P p1 = list.get(0);
        P p2 = list.get(1);
        P p3 = list.get(2);
        if (p1.getNumber() == p2.getNumber() && p2.getNumber() == p3.getNumber()) {
            return new PType(3, PType.TYPE_SHANZ, p1);
        }
        return null;
    }

    /**
     * 判断牌是否是一个对子
     */
    private static PType isTwo(List<P> list) {
        P p1 = list.get(0);
        P p2 = list.get(1);
        if (p1.getNumber() == p2.getNumber()) { // 判断的对子
            return new PType(2, PType.TYPE_DUIZI, p1);
        }
        if ((p1.getNumber() + p2.getNumber()) == 27) { // 判断王炸，和等于27
            return new PType(2, PType.TYPE_ZHADAN, p1);
        }
        return null;
    }

}
/****
 1张牌
 表示单个

 2张牌
 表示对子 或者 王炸

 3张牌
 三张

 4张牌
 炸弹  三带一

 5张牌
 顺子  三带一对

 6张牌
 顺子  连对  四带二  飞机

 7张牌
 顺子

 8张牌
 顺子 连对 飞机（三带一x2）

 9张牌
 顺子 飞机（三个三张）

 10张牌
 顺子 连对 飞机（三带二x2）

 11张牌
 顺子

 12张牌
 顺子 连对 飞机（三带一x3 | 4个三张）

 13张牌
 无

 14张牌
 连对

 15张牌
 飞机（5个三张|三带二x3）

 16张牌
 连对 飞机（三带一x4）

 17张牌
 无

 18张牌
 连对

 19张牌
 无

 20张牌
 连对 飞机（三带二x4 | 三带一x5）
 ***/