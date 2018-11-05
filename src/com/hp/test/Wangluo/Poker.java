package com.hp.test.Wangluo;

public class Poker implements Comparable<Poker>{
    private String color;
    private String name;//	A,2,3,4,5,6,7,8,9,10,J,Q,K,大王,小王
    private int num;//牌的大小

    public String getColor() {
        return color==null?"":color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    @Override
    public int compareTo(Poker o) {
        return this.num - o.num;
    }




}
