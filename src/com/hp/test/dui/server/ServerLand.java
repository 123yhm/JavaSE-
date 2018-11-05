package com.hp.test.dui.server;

import com.hp.test.DDZ.src.com.java1823.ddz.Action;
import com.hp.test.dui.action.ActionLand;
import com.hp.test.dui.action.LandControl;
import com.hp.test.dui.action.PlayingCard;
import com.hp.test.dui.action.PlayingType;
import com.hp.test.dui.util.UtilLand;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * 负责服务端的动作消息传送
 */
public class ServerLand {

    private final int port = 1;
    private ServerSocket serverSocket;
    private List<ServerDialogueClient> listClient;
    //计数不先出的次数
    private int discard;
    //重置用户手中牌的类
    private LandControl landControl=null;
    //客户端下标
    private int countClient = -1;
    private ServerDialogueClient serverDialogueClient=null;
    //转换出牌角色
    private int clientCount;

    public ServerLand() {
        try {
            this.listClient = new ArrayList<ServerDialogueClient>();
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            this.serverSocket = null;
            e.printStackTrace();
        }
    }

    //监听器
    public void acctextClient() {
        System.out.println("服务已启动--等待用户开始对子游戏");
        while (listClient.size() <2 ) {
            try {
                Socket accept = serverSocket.accept();
                ServerDialogueClient client = new ServerDialogueClient(this, accept);
                listClient.add(client);
                System.out.println("已有" + listClient.size() + "个用户");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //线程完毕，提示用户
        sendClientMassage("上线成功，请您输入开始标志--START");
    }

    //发送消息到客户端
    public void sendClientMassage(String msg) {
        for (ServerDialogueClient sdc : listClient) {
            sdc.sendClientMag(msg);
        }
    }

    //判断客户输入的消息
    public void startInputMsg(ServerDialogueClient sdc, String msg) {
        if (!ActionLand.judgeInput(msg)) {
            System.out.println("您的输入有误，请重新输入");
            return;
        } else if (listClient.size() < 2) {
            sdc.sendClientMag("请等待其他用户上线");
            return;
        }
        if (msg.equals("")) {
            sdc.sendClientMag("输入不能为空");

            return;
        }
        if(serverDialogueClient==null){//1
            if (msg.equals(ActionLand.GO)) {
                if (sdc.getGameState()<ServerDialogueClient.STATE_GO) {
                    sdc.setGameState(ServerDialogueClient.STATE_GO);
                    prepareUser(sdc);//准备
                }
            }
        }else {//2
            if(serverDialogueClient!=sdc){
                return;
            }
            if(sdc.getGameState()==ServerDialogueClient.STATE_BOSS){
                if (msg.equals(ActionLand.BOSS)) {//随机谁先出
                    orderUser();
                }
                if (msg.equals(ActionLand.PASS)) {//退出先出牌选择
                    clientCount++;
                    discard++;//计数
                    countClient++;
                    countClient=countClient%2;
                    serverDialogueClient = listClient.get(countClient);
                    if (discard>=2){
                        sendClientMassage("没人选择先出！洗牌开始");
                        startAgain();
                    }else{
                        sendClientMassage("玩家放弃！下一个》");
                        sendUaser(countClient);
                    }
                }
            }else if(sdc.getGameState()==ServerDialogueClient.STATE_OUT){
                if(ActionLand.judgeOut(msg)){
                    int cutout = cutout(msg);
                    if (cutout>=26&&cutout<0){
                        sendClientMassage("您的出牌无效，请重新输入");
                        return;
                    }
                    List<PlayingCard> list = UtilLand.startClientValue(serverDialogueClient.getListUser(), cutout);
                    if(list==null){
                        sendClientMassage("您的出牌无效，无法查到");
                        return;
                    }
                    PlayingType playingType=UtilLand.rulePlayingCare(list);
                    if (playingType==null){
                        System.out.println("你输入的牌不符合规则，请重新输入");
                        return;
                    }
                    UtilLand.removerValue(serverDialogueClient.getListUser(),list);
                    refreshP(sdc);//刷新牌
                    clientCount++;//下一个出牌的
                    countClient++;
                    countClient=countClient%2;
                    serverDialogueClient = listClient.get(countClient);
                    serverDialogueClient.sendClientMag("您可以出牌了");
                    orderUser();//
                }else{
                    serverDialogueClient.sendClientMag("请重新输入");
                }
            }
        }
    }
    //刷新牌
    public  void refreshP(ServerDialogueClient sdc){
            sdc.playingCareInterMsg();
            String value="";
        for (ServerDialogueClient sdg:listClient) {
            value+=sdg.getIdentityStr()+"剩余"+sdg.getListUser().size()+"张";
        }
        sendClientMassage(value);
    }
    //通过下标判断系统值
    public int cutout(String msg){
                String msgA=msg.replace("#","");
                msgA=msg.replace("@","");
                String[] str=msgA.split("\\D+");//截取非数字
                int[] index=new int[str.length];
                int valuess = 0;
                for (int in:index) {
                    valuess=in;
                }
        return valuess;
    }

    //谁先出
    private void orderUser() {
        clientCount=0;
        for (int i = 0; i < listClient.size(); i++) {
            ServerDialogueClient serverDialogueClient = listClient.get(i);
            serverDialogueClient.setGameState(ServerDialogueClient.STATE_OUT);
            if (i == countClient) {
                serverDialogueClient.playingCareInterMsg();
                serverDialogueClient.sendClientMag("请您输入#开始---下标@结束进行出牌");
            }else{
                serverDialogueClient.sendClientMag("等待出牌");
            }
        }
    }

    private void prepareUser(ServerDialogueClient serverDialogueClient) {
        String value="";
        for (ServerDialogueClient ss : listClient) {
            if(ss.getGameState()!=ServerDialogueClient.STATE_GO){
                value += ss.getIPAddr();
            }
        }
        if (!value.equals("")) {
            sendClientMassage(value + "用户没有准备");
            return;
        }
        //都不要--重新构建牌
        startAgain();
    }

    //判断
    private void startAgain() {
        for (ServerDialogueClient pc : listClient) {//用户手中的牌set为空
            pc.setListUser(null);
        }
        landControl = new LandControl();
        landControl.shuffle();
        List<List<PlayingCard>> deal = landControl.deal();
        for (int i = 0; i < 2; i++) {
            listClient.get(i).setListUser(deal.get(i));
        }
        for (ServerDialogueClient sd : listClient) {
            sd.setGameState(ServerDialogueClient.STATE_BOSS);
            sd.playingCareInterMsg();
        }
        //随机谁开始先出牌
        RandomPlayingCare();
    }
    //随机谁开始先出牌
    public void RandomPlayingCare() {
        Random random=new Random();
        int i = random.nextInt(2);
        discard=0;
        countClient= i;
        serverDialogueClient = listClient.get(countClient);
        sendUaser(i);
    }
        //谁先出发送到客户端
    public void sendUaser(int i){

        for (int j = 0; j <listClient.size() ; j++) {
            ServerDialogueClient serverDialogueClient = listClient.get(j);
            if(j==i){
                serverDialogueClient.sendClientMag("您先出，请输入#BOSS为先出牌，输入#PASS为后出牌");
            }else{
                serverDialogueClient.sendClientMag("倒计时-请您等待");
            }
        }
    }

}
