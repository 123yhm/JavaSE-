package com.hp.test.dou.rule;

/**
 * matches:告知此字符串是否匹配给定的正则表达式。
 */
import com.hp.test.dou.util.LandUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 牌规则
 */
public class LandRule {
    public static LandType ruleBool(List<Landlords> list) {
        int size = list.size();
        LandType result = null;
        switch (size) {
            case 1:
                result = new LandType(1, LandType.TYPE_DANG, list.get(0));
                break;
            case 2:
                result = istwo(list);
                break;
            case 3:
                result = isthre(list);
                break;
            case 4:
                result = isfour(list);
                break;
            case 5:
                result = isfive(list);
                break;//
            default:
                result = isSex(list);
                break;

        }
        return result;
    }




    /**
     * 6:顺子  连对  四带二  飞机
     *
     * @param list
     * @return
     */

    private static LandType isSex(List<Landlords> list) {
        LandUtil.sortplayingCare(list);
        LandType straight = isStraight(list);
        if (straight!=null) {
            return straight;
        }
        LandType evenFor = isEvenFor(list);
        if (evenFor!=null) {
            System.out.println("标志6---");
            return evenFor;
        }
        LandType Fwt = isFwt(list);
        if (Fwt!=null) {
            System.out.println("标志6---");
            return Fwt;
        }
        LandType Aircraft = isEvenFor(list);
        if (Aircraft!=null) {
            System.out.println("标志6---");
            return Aircraft;
        }
        return null;
    }

    /**
     * 5:顺子、三带二
     *
     * @param list
     * @return
     */
    public static LandType isfive(List<Landlords> list) {
        LandUtil.sortplayingCare(list);
        LandType straight = isStraight(list);
        if (straight!=null) {
            return straight;
        }
        for (int i = 0; i < list.size(); i++) {
            Landlords li = list.get(i);
            if (li.getPlayingIndex() >= 13) {
                return null;
            }
        }
        // 拼接数字
        String value = IntegerStringValue(list);
        String aaabb = "([0-9a-c])\\1{2}([0-9a-c])\\2{1}";// AAABB 形式一
        String aabbb = "([0-9a-c])\\1{1}([0-9a-c])\\2{2}";  // AABBB 形式二
        boolean matches = value.matches(aaabb);
        boolean matche = value.matches(aabbb);
        if (matche || matches) {
            System.out.println("标志5---");
            return new LandType(5,LandType.TYPE_SHAND,list.get(2));
        }
        return null;
    }

    /**
     * 4:判断炸弹和三带一
     */
    private static LandType isfour(List<Landlords> list) {
        LandUtil.sortplayingCare(list);
        String value = IntegerStringValue(list);
        if (value.matches("([0-9a-c])\\1{3}")) {
            System.out.println("标志4---");
            return new LandType(4,LandType.TYPE_ZHADAN,list.get(0));
        } else if (value.matches("[0-9a-c]*([0-9a-c])\\1{2}[0-9a-c]*")) {
            System.out.println("标志4---");
            return new LandType(4,LandType.TYPE_SHAND,list.get(1));
        }
        return null;
    }

    /**
     * 3:三张
     *
     * @param list
     * @return
     */
    private static LandType isthre(List<Landlords> list) {
        Landlords landlords = list.get(0);
        Landlords landlordss = list.get(1);
        Landlords landlord = list.get(2);
        if (landlords.getPlayingIndex() == landlordss.getPlayingIndex() && landlord.getPlayingIndex() == landlords.getPlayingIndex()) {
            System.out.println("标志3---");
             return new LandType(3,LandType.TYPE_SHANZ,landlords);
        }
        return null;
    }

    /**
     * 2:对子
     *
     * @param list
     * @return
     */
    private static LandType istwo(List<Landlords> list) {
        Landlords landlords = list.get(0);
        Landlords landlordss = list.get(1);
        if (landlords.getPlayingIndex() == landlordss.getPlayingIndex()) {
            System.out.println("标志2---");
            return new LandType(2,LandType.TYPE_DUIZI,landlords);
        }
        if (landlords.getPlayingIndex() + landlordss.getPlayingIndex() == 27) {
            System.out.println("标志2---0");
            return new LandType(2,LandType.TYPE_ZHADAN,landlords);
        }
        return null;
    }

    /**
     * 飞机
     *
     * @return
     */
    private static LandType isAircraft(List<Landlords> list) {
        LandUtil.sortplayingCare(list);
        if (list.size() < 6) {//牌的数量
            return null;
        }
        Landlords landlords = list.get(list.size() - 2);
        if (landlords.getPlayingIndex() == 13) {//牌中是否有王炸
            return null;
        }
        if (isEqualValue(list)) {//判断牌中是否有是个相同的
            return null;
        }
        List<Landlords> listLand = new ArrayList<>();
        int index = 1;
        Landlords landEqual = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            Landlords landlords1 = list.get(i);
            if (landlords1.getPlayingIndex() == landEqual.getPlayingIndex()) {
                index++;
            } else {
                landEqual = landlords1;
                index = 1;
            }
            if (index == 3 && landEqual.getPlayingIndex() != 12) {
                listLand.add(landEqual);
            }
        }
        if (listLand.size() < 2) {
            return null;
        }
        LandType landType = isStraight(listLand);
        if (landType == null) {
            return null;
        }
        if (listLand.size() * 3 == list.size()) {
            return new LandType(6,LandType.TYPE_FEIJI,list.get(listLand.size()-1));
        }
        if (listLand.size() * 3 + list.size() == list.size()) {
            return new LandType(6,LandType.TYPE_FEIJI,list.get(listLand.size()-1));
        }
        String s = IntegerStringValue(list);
        String s1 = s.replaceAll("([0-9a-c])\\1{2}", "");
        s1 = s1.replaceAll("([0-9a-c])\\1", "$1");
        if (s1.length() * 5 == s.length()) {
            return new LandType(6,LandType.TYPE_FEIJI,list.get(listLand.size()-1));
        }
        return null;
    }

    /**
     * 判断牌中是否有炸弹
     *
     * @return
     */
    private static Boolean isEqualValue(List<Landlords> list) {
        LandUtil.sortplayingCare(list);
        int index = 1;
        Landlords landlords = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            Landlords landvalue = list.get(i);
            if (landvalue.getPlayingIndex() == landlords.getPlayingIndex()) {
                index++;
            } else {
                landlords = landvalue;
                index = 1;
            }
            if (index == 4) {
                return true;
            }
        }
        return false;
    }

    /**
     * 四带二
     *
     * @return
     */
    private static LandType isFwt(List<Landlords> list) {
        //牌数
        if (list.size() != 6) {
            return null;
        }
        LandUtil.sortplayingCare(list);
        Landlords landvalue = list.get(4);//排序后根据下标查看后两个数是否是大小鬼
        Landlords landvalue2 = list.get(5);
        if (landvalue.getPlayingIndex() == 13 || landvalue2.getPlayingIndex() == 14) {
            return null;
        }
        String s = IntegerStringValue(list);
        String s1 = s.replaceAll("([0-9a-c])\\1{3}", "");//replacement:换此字符串所有匹配给定的正则表达式的子字符串。
        if (s1.length() == 2) {
            return new LandType(6,LandType.TYPE_SHIDE,list.get(2));
        }
        return null;
    }

    /**
     * 连对
     *
     * @return
     */
    private static LandType isEvenFor(List<Landlords> list) {
        LandUtil.sortplayingCare(list);
        if (list.size() < 6 || list.size() % 2 != 0) {
            return null;
        }
        Landlords value = null;
        Landlords value2 = null;
        for (int i = 0; i < list.size(); i += 2) {
            Landlords landvalue = list.get(i);
            Landlords landvalue2 = list.get(i + 1);
            if (landvalue.getPlayingIndex() != landvalue2.getPlayingIndex() || landvalue.getPlayingIndex() >= 12) {
                return null;
            }
            if (value != null || value2 != null) {
                if (landvalue.getPlayingIndex() - value.getPlayingIndex() != 1) {
                    return null;
                }
            }
            value = landvalue;
            value2 = landvalue2;
        }
        return new LandType(list.size(),LandType.TYPE_SHUNZI,list.get(list.size()-1));
    }

    /**
     * 顺子
     *
     * @param list
     * @return
     */
    public static LandType isStraight(List<Landlords> list) {
        Landlords index = list.get(0);
        LandUtil.sortplayingCare(list);
        for (int i = 0; i < list.size(); i++) {//顺子不允许拥有大小鬼
            Landlords landlords = list.get(i);
            if (landlords.getPlayingIndex() >= 12) {
                return null;
            }
        }
        for (int i = 1; i < list.size(); i++) {
            Landlords playingIndex = list.get(i);
            if (playingIndex.getPlayingIndex() - index.getPlayingIndex() != 1) {//顺子的前后差为一
                return null;
            }
            index = playingIndex;
        }
        return new LandType(list.size(),LandType.TYPE_SHANZ,list.get(list.size()-1));
    }

    /**
     * 将一串数字拼接成一串字符串
     */
    public static String IntegerStringValue(List<Landlords> list) {
        String value = "";
        for (Landlords landlords : list) {
            value += Integer.toHexString(landlords.getPlayingIndex());
        }
        return value;
    }
}
