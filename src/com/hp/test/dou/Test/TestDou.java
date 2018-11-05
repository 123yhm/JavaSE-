package com.hp.test.dou.Test;

import com.hp.test.dou.rule.LandControl;
import com.hp.test.dou.rule.LandRule;
import com.hp.test.dou.rule.LandType;
import com.hp.test.dou.util.LandUtil;
import com.hp.test.dou.rule.Landlords;

import java.util.List;

public class TestDou {
    public static void main(String[] args) {
        LandControl landControl = new LandControl();
        landControl.shuffle();
        List<List<Landlords>> deal = landControl.deal();//得到发牌的集合
        System.out.println("以下是今天游戏的扑克牌：");

        for (List<Landlords> list : deal) {
            System.out.println(list);
        }

        System.out.println("========================================================");
        //System.out.println("下面开始移数字：");
        List<Landlords> landlordsList = deal.get(0);
        System.out.println("获得牌已完成排序");
        LandUtil.sortplayingCare(landlordsList);//排序
       for (Landlords land : landlordsList) {
            System.out.print(land);
        }
        List<Landlords> landlordsList1 =
                LandUtil.startPlayingCare(landlordsList, new int[]{0, 1});
        System.out.println("根据下标取得的数字" + landlordsList1);
        //LandUtil.removePlayingCares(landlordsList, landlordsList1);
        //  System.out.println("被移出牌后的排序为："+landlordsList);
        System.out.println("==================================================================");
        System.out.println("规则验证：");
        LandRule.ruleBool(landlordsList1);
        List<Landlords> landlordsList2 =
                LandUtil.startPlayingCare(landlordsList, new int[]{0, 1});
        List<Landlords> landlordsList3 =
                LandUtil.startPlayingCare(landlordsList, new int[]{0, 1});
        System.out.println("比较大小");
        System.out.println(landlordsList2);
        System.out.println(landlordsList3);
        LandType landType = LandRule.ruleBool(landlordsList2);
        System.out.println(landType);
        LandType landType2 = LandRule.ruleBool(landlordsList2);
        if (LandType.isCompare(landType, landType2)){
            System.out.println("12");
            if (landType.compareTo(landType2)){//先出的大
                System.out.println("我大21");
            }else{
                System.out.println("我最大");
            }
            System.out.println("13");
        }else{

            System.out.println("无法比较");
        }
    }
}
