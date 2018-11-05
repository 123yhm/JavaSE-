package com.hp.test.dui.test;

import com.hp.test.dui.action.LandControl;
import com.hp.test.dui.action.PlayingCard;
import com.hp.test.dui.util.UtilLand;

import java.util.List;

public class TestPlaying {
    public static void main(String[] args) {
        LandControl land=new LandControl();
        List<List<PlayingCard>> deal = land.deal();
        land.shuffle();

        for (List<PlayingCard> list:deal) {
            for (PlayingCard li:list) {
                System.out.print(li);
            }
        }
        List<PlayingCard> list = deal.get(0);
        UtilLand.sortPlayingCare(list);
        System.out.println("\n"+"11111"+list);
        List<PlayingCard> list1 = UtilLand.startClientValue(list,2);
        UtilLand.rulePlayingCare(list1);
        UtilLand.removerValue(list,list1);//移除
        System.out.println("该值为："+list1);

        System.out.println("\n"+"----2"+list);
    }
}
