package com.hp.test.DDZ.src.com.java1823.ddz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PControl {

    // 牌的集合
    private List<P> listPs;

    public List<P> getListPs() {
        return listPs;
    }

    public void setListPs(List<P> listPs) {
        this.listPs = listPs;
    }

    public PControl() {
        this.listPs = new ArrayList<>();
        this.builderPs();
    }

    // 构建我们的扑克牌
    private void builderPs() {
        for (int i = 0; i < P.SHOWS.length - 2; i++) {
            P p1 = new P(0, i);
            P p2 = new P(1, i);
            P p3 = new P(2, i);
            P p4 = new P(3, i);
            listPs.add(p1);
            listPs.add(p2);
            listPs.add(p3);
            listPs.add(p4);
        }
        listPs.add(new P(4, 13));
        listPs.add(new P(4, 14));
    }

    // 定义洗牌的方法
    public void shuffle() {
        Collections.shuffle(this.listPs);
    }

    // 发牌的方法
    public List<List<P>> deal() {
        List<List<P>> list = new ArrayList<>();
        List<P> p1 = new ArrayList<>(); // 三个玩家
        List<P> p2 = new ArrayList<>();
        List<P> p3 = new ArrayList<>();
        List<P> di = new ArrayList<>(); // 地主的三张牌
        for (int i = 0; i < 51; i++) {  // 取51张牌
            int v = i % 3;
            if (v == 0) {
                p1.add(listPs.get(i));
            } else if (v == 1) {
                p2.add(listPs.get(i));
            } else if (v == 2) {
                p3.add(listPs.get(i));
            }
        }
        di.add(listPs.get(51));
        di.add(listPs.get(52));
        di.add(listPs.get(53));
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(di);
        return list;
    }

    // 发牌的方法  每三张三张的发牌 5次后 发两张
    public List<List<P>> deal2() {
        List<List<P>> list = new ArrayList<>();
        List<P> p1 = new ArrayList<>(); // 三个玩家
        List<P> p2 = new ArrayList<>();
        List<P> p3 = new ArrayList<>();
        List<P> di = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                List<P> tem = null;
                if (j % 3 == 0) {
                    tem = p1;
                } else if (j % 3 == 1) {
                    tem = p2;
                } else {
                    tem = p3;
                }
                tem.add(listPs.get(i * 9 + j * 3 + 0));
                tem.add(listPs.get(i * 9 + j * 3 + 1));
                tem.add(listPs.get(i * 9 + j * 3 + 2));
            }
        }
        // 45
        for (int j = 0; j < 3; j++) {
            List<P> tem = null;
            if (j % 3 == 0) {
                tem = p1;
            } else if (j % 3 == 1) {
                tem = p2;
            } else {
                tem = p3;
            }
            tem.add(listPs.get(45 + j * 3 + 0));
            tem.add(listPs.get(45 + j * 3 + 1));
        }
        di.add(listPs.get(51));
        di.add(listPs.get(52));
        di.add(listPs.get(53));
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(di);
        return list;
    }


}
