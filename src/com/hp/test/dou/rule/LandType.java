package com.hp.test.dou.rule;

/**
 * 制定规则比较大小
 * 数量
 * 类型：单个、对子、王炸、三张、三带一（三带一对）、顺子、连对 、 四带二 、 飞机
 * 大小
 */
public class LandType {
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
    private int number;
    private int landType;
    private Landlords maxnumber;

    public LandType(int number, int landType, Landlords maxnumber) {
        this.number = number;
        this.landType = landType;
        this.maxnumber = maxnumber;
    }

    //筛选两者是否可以比较
    public static Boolean isCompare(LandType tp1,LandType tp2){
        if (tp1==null&&tp2==null){
            return false;
        }
        if(tp1.getNumber()==tp2.getNumber()&&tp1.getLandType()==tp2.getLandType()){

            return true;
        }
        if(tp1.getLandType()==LandType.TYPE_ZHADAN ||tp2.getLandType()==LandType.TYPE_ZHADAN){

            return true;
        }

        return false;
    }

    //类型比较
    public Boolean compareTo(LandType landType){
        if(this.getMaxnumber().getPlayingIndex()>=this.getMaxnumber().getPlayingIndex()){
            return true;
        }
        return false;
    }
    public int getNumber() {

        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    public int getLandType() {
        return landType;
    }

    public void setLandType(int landType) {
        this.landType = landType;
    }

    public Landlords getMaxnumber() {
        return maxnumber;
    }

    public void setMaxnumber(Landlords maxnumber) {
        this.maxnumber = maxnumber;
    }
}
