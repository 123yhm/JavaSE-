package com.hp.test.dou.util;

import com.hp.test.dou.rule.Landlords;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 牌移除、排序
 */
public class LandUtil {
    /**
     * 排序
     */
    public static void sortplayingCare(List<Landlords> list3) {
        Collections.sort(list3);
    }

    /**
     * @param list  表示用户手中的牌
     * @param input 表示要取的牌的索引数组
     * @return 返回取到的牌
     */
    public static List<Landlords> startPlayingCare(List<Landlords> list, int[] input) {
        List<Landlords> startPlayingCare = new ArrayList<>();
        for (int li : input) {
            Landlords landlords = list.get(li);
            startPlayingCare.add(landlords);
        }
        return startPlayingCare;
    }

    /**
     * @param landlordsList    表示用户手中的牌
     * @param startPlayingCare 表示要移出的牌s
     */
    public static void removePlayingCares(List<Landlords> landlordsList,
                                          List<Landlords> startPlayingCare) {
        landlordsList.removeAll(startPlayingCare);
    }
}
