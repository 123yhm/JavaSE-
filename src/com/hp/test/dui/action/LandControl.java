package com.hp.test.dui.action;

import com.hp.test.dou.rule.Landlords;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 控制--
 * 初始化51
 * 洗牌、
 * 发牌
 *
 */
public class LandControl {
    private List<PlayingCard> listplay;

    public List<PlayingCard> getListplay() {
        return listplay;
    }

    public void setListplay(List<PlayingCard> listplay) {
        this.listplay = listplay;
    }

    /**
     * 在初始化该类时并调用构牌动作
     */
    public LandControl() {
        this.listplay = new ArrayList();
        this.definitionPlaying();
    }

    /**
     * 构建一副牌
     */
    public void definitionPlaying() {
        //该循环构建52张花色牌
        for (int i = 0; i < PlayingCard.plyingName.length; i++) {

            PlayingCard land = new PlayingCard(0, i);
            PlayingCard land1 = new PlayingCard(1, i);
            PlayingCard land2 = new PlayingCard(2, i);
            PlayingCard land3 = new PlayingCard(3, i);
            listplay.add(land);
            listplay.add(land1);
            listplay.add(land2);
            listplay.add(land3);
        }
        //小鬼和大鬼

    }

    //洗牌
    public void shuffle() {
        Collections.shuffle(this.listplay);
    }

    //发牌
    public List<List<PlayingCard>> deal() {

        List<List<PlayingCard>> list = new ArrayList<>();

        List<PlayingCard> listUser1 = new ArrayList<>();
        List<PlayingCard> listUser2 = new ArrayList<>();


        for (int i = 0; i < 52; i++) {  // 取52张牌
            int v = i % 2;
            if (v == 0) {
                listUser1.add(listplay.get(i));
            } else if (v == 1) {
                listUser2.add(listplay.get(i));
            }
        }

        //将用户加入集合中
        list.add(listUser1);
        list.add(listUser2);
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
