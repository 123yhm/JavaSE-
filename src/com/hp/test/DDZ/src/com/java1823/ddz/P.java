package com.hp.test.DDZ.src.com.java1823.ddz;

/**
 * 牌类对象
 * 属性：花色
 * 属性：数字
 */
public class P implements Comparable<P> {

    // 显示值
    public static final String[] SHOWS = {
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "J",
            "Q",
            "K",
            "A",
            "2",
            "N",    // 小鬼
            "M"     // 大鬼
    };

    public static final String[] TYPES = {
            "黑", "红", "梅", "方", "王"
    };


    private int type;       // 黑0 红1 梅2 方3 王4
    private int number;     // 0 1 2 3 4 5 6 7  8 9 A B C D E
    private String value;   // 3 4 5 6 7 8 9 10 J Q K A 2 N M

    public P(int type, int number) {
        this.type = type;
        this.number = number;
        this.value = SHOWS[this.number];
    }

    // 展示牌的内容
    public String show() {
        return this.TYPES[this.type] + this.value;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.show();
    }

    @Override
    public int compareTo(P o) {
        if (o == this) { // 比较内存引用
            return 0;
        }
        if (this.number - o.number == 0) { // 如果牌的大小相等，比花色
            return o.type - this.type; // 判断花色
        }
        return this.number - o.number;  // 比较的牌的大小
    }
}
