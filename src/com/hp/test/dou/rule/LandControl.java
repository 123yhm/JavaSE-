package com.hp.test.dou.rule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 控制--
 * 初始化54
 * 洗牌、
 * 发牌
 * --底部留三张牌
 */
public class LandControl {
    private List<Landlords> listPlaying;

    public List<Landlords> getListPlaying() {
        return listPlaying;
    }

    public void setListPlaying(List<Landlords> listPlaying) {
        this.listPlaying = listPlaying;
    }

    /**
     * 在初始化该类时并调用构牌动作
     */
    public LandControl() {
        this.listPlaying = new ArrayList();
        this.definitionPlaying();
    }

    /**
     * 构建一副牌
     */
    public void definitionPlaying() {
        //该循环构建52张花色牌
        for (int i = 0; i < Landlords.playingNumber.length; i++) {
            Landlords land = new Landlords(0, i);
            Landlords land1 = new Landlords(1, i);
            Landlords land2 = new Landlords(2, i);
            Landlords land3 = new Landlords(3, i);
            listPlaying.add(land);
            listPlaying.add(land1);
            listPlaying.add(land2);
            listPlaying.add(land3);

        }
       //小鬼和大鬼
        listPlaying.add(new Landlords(4, 13));
        listPlaying.add(new Landlords(4, 14));
    }

    //洗牌
    public void shuffle() {
        Collections.shuffle(this.listPlaying);
    }

    //发牌
    public List<List<Landlords>> deal() {

        List<List<Landlords>> list = new ArrayList<>();
        //三个用户
        List<Landlords> listUser1 = new ArrayList<>();
        List<Landlords> listUser2 = new ArrayList<>();
        List<Landlords> listUser3 = new ArrayList<>();
        //存储最后的三张底牌
        List<Landlords> Cards = new ArrayList<>();
        for (int i = 0; i < 51; i++) {  // 取51张牌
            int v = i % 3;
            if (v == 0) {
                listUser1.add(listPlaying.get(i));
            } else if (v == 1) {
                listUser2.add(listPlaying.get(i));
            } else if (v == 2) {
                listUser3.add(listPlaying.get(i));
            }
        }
        //底牌取值
        Cards.add(listPlaying.get(51));
        Cards.add(listPlaying.get(52));
        Cards.add(listPlaying.get(53));
        //将用户加入集合中
        list.add(listUser1);
        list.add(listUser2);
        list.add(listUser3);
        list.add(Cards);
        return list;
    }
    /*public List<List<Landlords>> deal(){
        List<List<Landlords>> list=new ArrayList<>();
        //三个用户
        List<Landlords> listUser1=new ArrayList<>();
        List<Landlords> listUser2=new ArrayList<>();
        List<Landlords> listUser3=new ArrayList<>();
        //存储最后的三张底牌
        List<Landlords> Cards=new ArrayList<>();
        //该循环是每个用户发送三张牌
        for (int i = 0; i <5 ; i++) {//扣除3张底牌、每个用户可以发17张牌、地主拥有最后3张公二十张
            for (int j = 0; j <3 ; j++) {
                List<Landlords> result=null;
                if(j%3==0){
                    result=listUser1;
                }else
                if(j%3==1){
                    result=listUser2;
                }else{

                    result=listUser3;
                }
                result.add(listPlaying.get(i * 9 + j * 3 + 0));
                result.add(listPlaying.get(i * 9 + j * 3 + 1));
                result.add(listPlaying.get(i * 9 + j * 3 + 2));
            }
        }
    //最后一次每个用户只能发两张牌
        for (int j = 0; j <3 ; j++) {
            List<Landlords> result=null;
            if(j%3==0){
                result=listUser1;
            }else
            if(j%3==1){
                result=listUser2;
            }else{
                result=listUser3;
            }
            //?????
            result.add(listPlaying.get(45 + j * 3 + 0));
            result.add(listPlaying.get(45 + j * 3 + 0));

        }
        //底牌取值
        Cards.add(listPlaying.get(51));
        Cards.add(listPlaying.get(52));
        Cards.add(listPlaying.get(53));
        //将用户加入集合中
        list.add(listUser1);
        list.add(listUser2);
        list.add(listUser3);
        list.add(Cards);
        return list;
    }*/
}
