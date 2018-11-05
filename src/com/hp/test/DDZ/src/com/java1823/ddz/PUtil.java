package com.hp.test.DDZ.src.com.java1823.ddz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

// 牌的工具类
public class PUtil {


    /**
     * 将牌进行排序
     *
     * @param list
     */
    public static void sortP(List<P> list) {
        Collections.sort(list);
    }


    /**
     * @param list 表示用户手中的牌
     * @param idxs 表示要取的牌的索引数组
     * @return 返回取到的牌
     */
    public static List<P> fetchP(List<P> list, int[] idxs) {
        if (!isRepeat(idxs)) {
            return null;
        }
        List<P> fetchP = new ArrayList<>();
        for (int i : idxs) {
            if (i >= list.size()) { // 索引超出牌的数量，会引起数组越界
                return null;
            }
            P p = list.get(i);
            fetchP.add(p);
        }
        return fetchP;
    }


    private static boolean isRepeat(int[] idxs) {
        HashSet<String> strSet = new HashSet<>();
        for (int i : idxs) {
            strSet.add(i + "");
        }
        return strSet.size() == idxs.length;
    }


    /**
     * @param list   表示用户手中的牌
     * @param fetchP 表示要移出的牌
     */
    public static void removeP(List<P> list, List<P> fetchP) {
        list.removeAll(fetchP);
    }


}
