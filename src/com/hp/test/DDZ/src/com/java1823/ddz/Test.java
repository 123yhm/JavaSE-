package com.hp.test.DDZ.src.com.java1823.ddz;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Test {


    public static void main(String[] args) {

//        Random random = new Random();
//        for (int i = 0; i <10000; i++) {
//            System.out.println(random.nextInt(3));
//        }


//        int[] arr = parseToIndex("#OUT#  1, 2, 3,  4,");

//        System.out.println(Arrays.toString(arr));


        DDZServer server = new DDZServer();
        server.accept();


//        PControl pControl = new PControl();
//
//        List<P> listPs = pControl.getListPs();
//        System.out.println(listPs);
//
//        List<P> ps1 = PUtil.fetchP(listPs, new int[]{1, 2, 3, 24, 5, 6, 7, 44});

//        List<P> ps2 = PUtil.fetchP(listPs, new int[]{13, 14, 15, 16, 17, 18, 28, 33});


//        System.out.println(ps1);
        ///    System.out.println(ps2);

//        PType pType1 = PRule.judgeP(ps1);  // 这个表示先出的牌
//        PType pType2 = PRule.judgeP(ps2);   // 表示后出的牌
//
//        if (PType.isCompare(pType1, pType2)) {
//            if (pType1.compareTo(pType2)) {
//                System.out.println("先出的牌大");
//            } else {
//                System.out.println("后出的牌大");
//            }
//        } else {
//            System.out.println("不能进行比较");
//        }

    }


//    private static int[] parseToIndex(String msg) {
//        String nMsg = msg.replace("#OUT#", "");
//        nMsg = nMsg.replace("#", "");
//        // 1, 2, 3, 4,
//        String[] split = nMsg.split(",");
//        int[] index = new int[split.length];
//        for (int i = 0; i < index.length; i++) {
//            index[i] = Integer.parseInt(split[i].trim());
//        }
//        return index;
//    }

}
