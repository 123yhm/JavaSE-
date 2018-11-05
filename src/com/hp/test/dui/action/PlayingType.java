package com.hp.test.dui.action;


/**
 * 牌的类型
 */
public class PlayingType {
    public static final int  playing_Single=1;
    private int typepaying;//类型
    private int numpaying;//数量
    private PlayingCard numberValue;
    public PlayingType(int typepaying,int numpaying,PlayingCard numberValue){
        this.typepaying=typepaying;
        this.numpaying=numpaying;
        this.numberValue=numberValue;
    }

    //类型比较
    public Boolean compareTo(PlayingType landType){
       if (this.getNumberValue().getNumber()==this.getNumberValue().getNumber()){
                return true;
       }
        return false;
    }

    //查看是否可以比较
    public static Boolean isNotCompare(PlayingType p1,PlayingType p2){
        if (p1.getNumpaying()==p2.getNumpaying()&&p1.getTypepaying()==p2.getTypepaying()){
            return true;
        }
        if (p1==null&&p2==null){
            return false;
        }
        return false;
    }

    public int getTypepaying() {
        return typepaying;
    }

    public void setTypepaying(int typepaying) {
        this.typepaying = typepaying;
    }

    public int getNumpaying() {
        return numpaying;
    }

    public void setNumpaying(int numpaying) {
        this.numpaying = numpaying;
    }

    public PlayingCard getNumberValue() {
        return numberValue;
    }

    public void setNumberValue(PlayingCard numberValue) {
        this.numberValue = numberValue;
    }
}
