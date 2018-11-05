package com.hp.test.dou.rule;


/**
 * 斗地主创建牌对象、属性：数字、数字属性、--逻辑数字
 */
public class Landlords implements Comparable<Landlords> {
    /**
     * N:小鬼
     * M：大鬼
     * 前台显示扑克牌的数字
     */
    public static final String[] playingNumber = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2", "N", "M"};
    /**
     * 花色
     */
    public static final String[] playingFlower = {"♠", "♥", "♣", "♦", "王"};
    private int Flower;//花色下标
    private int playingIndex;//系统对应值下标
    private String playingPage;//页面值

    /**
     * @param Flower
     * @param playingIndex 通过构造方法实现前后台值的下标展示对应的数字进行显示
     */
    Landlords(int Flower, int playingIndex) {
        this.Flower = Flower;
        this.playingIndex = playingIndex;
        this.playingPage = playingNumber[this.playingIndex];
    }

    /**
     * 展示牌的内容
     */
    public String playingValueShow() {
        return this.playingFlower[this.Flower] + this.playingPage;
    }

    /**
     * @return 以字符串的形式进行展示内容
     */
    @Override
    public String toString() {
        return this.playingValueShow();
    }

    @Override
    public int compareTo(Landlords o) {
        if (o == this) {
            return 0;
        }
        if (this.playingIndex - o.playingIndex == 0) {
            return o.Flower - this.Flower;
        }
        return this.playingIndex - o.playingIndex;

    }

    public int getFlower() {
        return Flower;
    }

    public void setFlower(int flower) {
        Flower = flower;
    }

    public int getPlayingIndex() {
        return playingIndex;
    }

    public void setPlayingIndex(int playingIndex) {
        this.playingIndex = playingIndex;
    }

    public String getPlayingPage() {
        return playingPage;
    }

    public void setPlayingPage(String playingPage) {
        this.playingPage = playingPage;
    }
}
