package com.hp.test.Wangluo;


import com.hp.test.Wangluo.Constant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PlayPoker {
    /**
     * MethodName: main
     * @Description: 三人斗地主
     * @param args
     * @return void
     * @author yangsy
     * @version:0.1
     * @date 2016-12-3 下午2:07:13
     */
    public static void main(String[] args) {
        //第一步：去买一副牌
        List<Poker> newPoker = newPoker();
        //第二步：洗牌
        cashPoker(newPoker);
        //第三步：发牌
        //定义三个角色
        Person p1 = new Person();
        p1.setRoleName("刘备");
        Person p2 = new Person();
        p2.setRoleName("关羽");
        Person p3 = new Person();
        p3.setRoleName("张飞");
        //底牌
        List<Poker> diPai = sendPoker(newPoker,p1,p2,p3);

        //第四步：选地主
        p1.setRoletType(Constant.DI_ZHU);
        System.out.println("本局"+p1.getRoleName()+"是地主");
        p2.setRoletType(Constant.NOT_DI_ZHU);
        p3.setRoletType(Constant.NOT_DI_ZHU);
        //把底牌给地主
        List<Poker> pList = p1.getPokerList();//把地主的牌拿出来
//		for (int i = 0; i < diPai.size(); i++) {
//			pList.add(diPai.get(i));
//		}
        pList.addAll(diPai);
        Collections.sort(pList);//重新排序
        System.out.println();
        //第五步：出牌
        startPlay(p1, p2, p3);

    }
    public static void startPlay(Person p1,Person p2,Person p3){
        System.out.println("********************************开始斗地主***********************");
        System.out.println();
        //谁是地主，先出牌
        List<Person> personList = new ArrayList<>();
        personList.add(p1);
        personList.add(p2);
        personList.add(p3);
        Scanner scanner = new Scanner(System.in);
        //是否结束
        boolean isEnd = false;
        Person person = null;//玩家
        List<Poker> pokerList = null;//玩家的牌
        Poker poker = null;//本次出的牌
        int beforeNum = -1;//上家出的牌的大小
        while(!isEnd){
            for (int i = 0; i < personList.size(); i++) {
                person = personList.get(i);
                pokerList = person.getPokerList();
                System.out.println(person.getRoleName()+"开始出牌,请输入1-"+pokerList.size());
                //System.out.println(personList.get(i).getRoleName()+"的牌是：");
                printPoker(pokerList);
                int index = scanner.nextInt();
                poker = pokerList.get(index-1);//本次出的牌
                System.out.println();
                //判断本次出的牌的大小是否大于上家出的牌
                while(poker.getNum()<=beforeNum){
                    System.out.println("您本次出的牌小于上家的牌，请重新出牌");
                    index = scanner.nextInt();
                    poker = pokerList.get(index-1);//本次出的牌
                }
                //正常出牌
                System.out.println(person.getRoleName()+"本次出的是："+poker.getColor()+poker.getName());
                beforeNum = poker.getNum();//记录本次出的牌的大小，用于给下家出牌时进行比较
                pokerList.remove(index-1);//把本次出的牌从手里删掉



            }
        }

    }
    public static void printPoker(List<Poker> list){
        for (int i = 0; i < list.size(); i++) {
            System.out.print(i+1+list.get(i).getColor()+list.get(i).getName()+"  ");
        }
        System.out.println();
    }
    /**
     * MethodName: sendPoker
     * @Description: 发牌，并返回3张底牌
     * @param list
     * @param p1
     * @param p2
     * @param p3
     * @return List<Poker>
     * @author yangsy
     * @version:0.1
     * @date 2016-12-3 下午3:10:58
     */
    public static List<Poker> sendPoker(List<Poker> list,Person p1,Person p2,Person p3){
        //第一个人分到的牌
        List<Poker> pList1 = new ArrayList<Poker>();
        //第二个人分到的牌
        List<Poker> pList2 = new ArrayList<Poker>();
        //第三个人分到的牌
        List<Poker> pList3 = new ArrayList<Poker>();
        //底牌
        List<Poker> diPai = new ArrayList<Poker>();
        for (int i = 0; i < list.size(); i++) {
            if(i<list.size()-3){
                pList1.add(list.get(i));
                pList2.add(list.get(++i));
                pList3.add(list.get(++i));
            }else {
                diPai.add(list.get(i));
            }
        }
        Collections.sort(pList1);
        Collections.sort(pList2);
        Collections.sort(pList3);
        p1.setPokerList(pList1);
        p2.setPokerList(pList2);
        p3.setPokerList(pList3);
        return diPai;
    }
    /**
     * MethodName: cashPoker
     * @Description: 洗牌：随机打乱一副牌的顺序
     * @param list
     * @return List<Poker>
     * @author yangsy
     * @version:0.1
     * @date 2016-12-3 下午2:34:36
     */
    public static void cashPoker(List<Poker> list){
        //利用集合工具类，随机打乱顺序
        Collections.shuffle(list);
    }
    /**
     * MethodName: newPoker
     * @Description: 生成一副新牌
     * @return List<Poker>
     * @author yangsy
     * @version:0.1
     * @date 2016-12-3 下午2:16:21
     */
    public static List<Poker> newPoker(){
        List<Poker> list = null;
        Poker poker = null;
        String [] pokerName = {"3","4","5","6","7","8","9","10","J","Q","K","A","2"};
        String [] pokerColor = {"黑桃","红桃","梅花","方块"};
        list = new ArrayList<Poker>();
        for (int i = 0; i < pokerColor.length; i++) {
            for (int j = 0; j < pokerName.length; j++) {
                poker = new Poker();
                poker.setColor(pokerColor[i]);
                poker.setName(pokerName[j]);
                poker.setNum(j);
                list.add(poker);
            }
        }
        //特殊处理大小王
        Poker xiaoWang = new Poker();
        xiaoWang.setName("小王");
        xiaoWang.setNum(14);
        Poker daWang = new Poker();
        daWang.setName("大王");
        daWang.setNum(15);
        list.add(xiaoWang);
        list.add(daWang);
        return list;
    }


}
