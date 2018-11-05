package com.hp.test.dui.util;
import com.hp.test.dui.action.PlayingCard;
import com.hp.test.dui.action.PlayingType;


import java.util.*;

/**
 * 1.排序
 * 2.获取用户抽取的牌
 * 3.移除自己相对应的牌
 */
public class UtilLand {
    //排序
    public static void sortPlayingCare(List<PlayingCard> listplay){
        Collections.sort(listplay);
    }
    //出牌规则
    public static PlayingType rulePlayingCare(List<PlayingCard> listplay){
        PlayingType playingType=null;
        if(listplay==null){
            return playingType=null;
        }
        int care=listplay.size();
            if (care == 1) {
                playingType = new PlayingType(PlayingType.playing_Single, 1, listplay.get(0));
                System.out.println("恭喜");
            } else if (care >= 26) {
                System.out.println("对不起！您出的牌不符合规则");
                return playingType = null;

        }
        return  playingType;
    }
    //取值
    public static List<PlayingCard> startClientValue(List<PlayingCard> list, int  index){
        List<PlayingCard> listvalue=new ArrayList<>();
        try {

        }catch (IndexOutOfBoundsException e){
            System.out.println("你的输入不符合出牌规则："+e.getMessage());
        }
        int value=index;
        if(value<0||value>=26){
            System.out.println("下标越界，请重新输入：");
            return listvalue=null;
        }else{
            PlayingCard playingValue=list.get(value);
            listvalue.add(playingValue);


        }
        return listvalue;
    }
    //移除
    public static void removerValue(List<PlayingCard> list,List<PlayingCard> listvalue){
        if (listvalue==null){
            return;
        }
        list.removeAll(listvalue);
    }
    //去重

    /**
     *
     * @param list :自己手中的牌
     * @param list1 ：在对方抽的牌
     * @return
     */
    public static List<PlayingCard> deWeight(List<PlayingCard> list,List<PlayingCard> list1){
        //Set<PlayingCard> set=new HashSet();
        //创建一个不重复的list集合
        list=new ArrayList<>();
        for (PlayingCard pd:list1) {
            if(!list.contains(pd)){
                list.add(pd);
            }
        }
    return list;
    }
}
