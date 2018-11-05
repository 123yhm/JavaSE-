package com.hp.test.dui.action;

/**
 * 牌
 * 对子游戏
 * 玩法：两人一组，将牌发完后，甲抽乙的一张牌，然后甲从自己
 * 的牌中找出和抽出的牌的数一样的配对后都放在一边不可再用。然后
 * 轮到乙抽甲的一张牌配对。依次类推，先配完者为胜。
 * 1.除去大小鬼
 * 2.牌对象
 * 3.花色
 * 4.页面数字
 * 5.系统数字
 */
public class PlayingCard implements Comparable<PlayingCard>{
    public static  final  String[] plyingName={"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};
    public static final String[] plyingFlower={"♠", "♥", "♣", "♦"};
    private int flower;
    private int number;
    private String systemNumber;

    public PlayingCard(int flower, int number) {
        this.flower = flower;
        this.number = number;
        this.systemNumber=plyingName[this.number];
    }
    public  String plyingCareShow(){
        return  plyingFlower[this.flower]+this.systemNumber;
    }
    @Override
    public int compareTo(PlayingCard o) {
        if (o == this) {
            return 0;
        }
        if (this.number - o.number == 0) {
            return o.flower - this.flower;
        }
        return this.number - o.number;
    }
    @Override
    public String toString() {
        return plyingCareShow();
    }

    public int getFlower() {
        return flower;
    }

    public void setFlower(int flower) {
        this.flower = flower;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSystemNumber() {
        return systemNumber;
    }

    public void setSystemNumber(String systemNumber) {
        this.systemNumber = systemNumber;
    }


}
