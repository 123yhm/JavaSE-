package com.hp.test.DDZ.src.com.java1823.ddz;

public class PType {

    // 斗地主牌类型的常量
    public static final int TYPE_DANG = 1;
    public static final int TYPE_DUIZI = 2;
    public static final int TYPE_SHANZ = 3;
    public static final int TYPE_SHAND = 4;
    public static final int TYPE_SHUNZI = 5;
    public static final int TYPE_LIANDUI = 6;
    public static final int TYPE_FEIJI = 7;
    public static final int TYPE_SHIDE = 8;
    public static final int TYPE_ZHADAN = 9;

    private int size;   // 牌的数量
    private int type;   // 单张 对子 三张 三带一 三带一对 顺子 连对 飞机 四带二 炸弹
    private P maxP;     // 最大的牌

    public PType(int size, int type, P maxP) {
        this.size = size;
        this.type = type;
        this.maxP = maxP;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public P getMaxP() {
        return maxP;
    }

    public void setMaxP(P maxP) {
        this.maxP = maxP;
    }


    // 牌类型比较的方法
    public boolean compareTo(PType p) {
        if (this.maxP.getNumber() > p.maxP.getNumber()) {
            return true;
        }
        return false;
    }

    // 判断两个类型的牌能不能进行比较
    public static boolean isCompare(PType p1, PType p2) {
        if (p1.getType() == PType.TYPE_ZHADAN
                || p2.getType() == PType.TYPE_ZHADAN) {
            return true;
        }
        if (p1.getSize() == p2.getSize() &&
                p1.getType() == p2.getType()) {
            return true;
        }
        return false;
    }

}
