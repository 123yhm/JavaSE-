package com.hp.test.DDZ.src.com.java1823.ddz;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DDZServer {

    private final int port = 9999;  // 服务端口
    private ServerSocket server;    // 服务端套接字
    private List<DDZClient> listClients;
    // 处理牌的类
    private PControl control;
    // 记录地主底牌
    private List<P> listDZ;

    // 能说话的对象
    private int canTalkIndex = -1;
    private DDZClient canTalkClient = null;

    // 记录住出的牌的类型
    private PType prevType = null;
    // 记录住出牌的人的索引值
    private int outIndex = -1;

    // 记录放弃地主的次数
    private int cntPass = 0;

    // 统计出牌的次数
    private int cntOut = 0;

    public DDZServer() {
        try {
            this.listClients = new ArrayList<>();
            this.server = new ServerSocket(port);
        } catch (IOException e) {
            this.server = null;
            e.printStackTrace();
        }
    }

    // 监听用户的连接
    public void accept() {
        System.out.println("服务已经启动，开始监听用户的连接");
        while (listClients.size() < 1) {
            try {
                Socket accept = server.accept();
                DDZClient ddzClient = new DDZClient(this, accept);
                listClients.add(ddzClient);
                System.out.println("有用户连接服务端");
                notifyAllUser("有" + listClients.size() + "用户连接了服务");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        notifyAllUser("输入 #GO 开始游戏");
    }

    // 发送信息到客户端
    public void notifyAllUser(String msg) {
        for (DDZClient client : listClients) {
            client.sendMsgToClient(msg);
        }
    }

    // 用来处理客户端发送过来的消息
    public void execCommand(DDZClient client, String msg) {
        if (!Action.judgeInput(msg)) {
            System.out.println("客户端发送了错误指令：" + msg);
            return;
        }
        if (listClients.size() < 3) {
            client.sendMsgToClient("请等待其他用户加入游戏");
            return;
        }
        if (canTalkClient == null) { // 表示游戏用户准备阶段
            if (msg.equals(Action.GO)) {
                if (client.getGameState() < DDZClient.STATE_GO) {
                    client.setGameState(DDZClient.STATE_GO); // 表示将用户设置成准备状态
                    judgeGoState(client); // 判断用户的准备状态
                }
            }
        } else { // 表示抢地主和游戏进行状态
            if (canTalkClient != client) {
                return;
            }
            if (client.getGameState() == DDZClient.STATE_BOSS) {
                if (msg.equals(Action.BOSS)) {  // 表示当前用户要地主
                    startGame();
                }
                if (msg.equals(Action.PASS)) {  // 表示当前用户放弃了地主
                    cntPass++;
                    canTalkIndex++;
                    canTalkIndex = canTalkIndex % 3;
                    canTalkClient = listClients.get(canTalkIndex);
                    if (cntPass >= 3) { // 大于等于3需要重新开始游戏
                        notifyAllUser("没有人选择地主，游戏重新开始");
                        reGo();
                    } else { // 小于3的情况，要提示下一个用户是否抢地主
                        notifyAllUser("有人放弃地主，请下一个玩家选择");
                        dzStateMsg(canTalkIndex);
                    }
                }
            } else if (client.getGameState() == DDZClient.STATE_OUT) { // 表示出牌状态
                if (msg.equals(Action.PASS)) { // 表示不要
                    if (cntOut <= 0) {
                        canTalkClient.sendMsgToClient("你是地主，你必须出牌");
                        return;
                    }

                    if (outIndex >= 0 && outIndex < listClients.size() && listClients.get(outIndex) == client) { // 表示上一个出牌的是与这次出牌的人是同一个人，不能放弃出牌
                        client.sendMsgToClient("不能放弃出牌");
                        return;
                    }
                    notifyAllUser(canTalkClient.getIdsStr() + "放弃了出牌");
                    cntOut++;
                    // 表示玩家出完牌了
                    canTalkIndex++;
                    canTalkIndex = canTalkIndex % 3;
                    canTalkClient = listClients.get(canTalkIndex);
                    canTalkClient.sendMsgToClient("你可以开始出牌了");

                } else if (Action.judgeOut(msg)) { // 表示出牌
                    int[] ints = parseToIndex(msg);
                    if (ints == null) {
                        canTalkClient.sendMsgToClient("出的牌有异常");
                        return;
                    }
                    List<P> ps = PUtil.fetchP(canTalkClient.getListPs(), ints);
                    if (ps == null) {
                        canTalkClient.sendMsgToClient("出的牌有异常");
                        return;
                    }
                    PType pType = PRule.judgeP(ps);
                    if (pType == null) {
                        canTalkClient.sendMsgToClient("出的牌有异常");
                        return;
                    }
                    if (prevType == null) {
                        notifyAllUser(canTalkClient.getIdsStr() + ":" + ps.toString());
                        prevType = pType;
                        outIndex = canTalkIndex; // 记录住出牌的人
                    } else {
                        if (outIndex == canTalkIndex) {
                            notifyAllUser(canTalkClient.getIdsStr() + ":" + ps.toString());
                            prevType = pType;
                            outIndex = canTalkIndex; // 记录住出牌的人
                        } else {
                            if (PType.isCompare(prevType, pType)) {
                                if (pType.compareTo(prevType)) {  // 表示这次出的牌大于上次出的牌
                                    notifyAllUser(canTalkClient.getIdsStr() + ":" + ps.toString());
                                    prevType = pType;
                                    outIndex = canTalkIndex; // 记录住出牌的人
                                } else {
                                    canTalkClient.sendMsgToClient("你出牌的牌有误,请重新出牌");
                                    return;
                                }
                            } else {
                                canTalkClient.sendMsgToClient("你出牌的牌有误,请重新出牌");
                                return;
                            }
                        }
                    }
                    // 移除出掉的牌
                    PUtil.removeP(client.getListPs(), ps);
                    //
                    DDZClient cc = judgeWinner();
                    if (cc != null) {
                        sendWinderToAll(cc);
                        return;
                    }
                    //
                    refreshP(client);
                    // 这里把出牌的权利 交给了下一个人
                    cntOut++;
                    // 表示玩家出完牌了
                    canTalkIndex++;
                    canTalkIndex = canTalkIndex % 3;
                    canTalkClient = listClients.get(canTalkIndex);
                    canTalkClient.sendMsgToClient("你可以开始出牌了");
                } else {
                    canTalkClient.sendMsgToClient("请重新输入");
                }
            }
        }
    }

    private void sendWinderToAll(DDZClient client) {
        if (client.getIdsStr().equals("地主")) {
            notifyAllUser("地主获得了游戏的胜利，农民起义失败了");
        } else {
            notifyAllUser("农民获得了游戏的胜利，地主被推翻了");
        }
    }


    // 判断有没有用户出完牌
    private DDZClient judgeWinner() {
        for (DDZClient cc : listClients) {
            if (cc.getListPs().size() <= 0) { // 表示有用户牌出完了
                return cc;
            }
        }
        return null;
    }

    // 刷新手中的牌
    private void refreshP(DDZClient client) {
        client.p2RemoteClient();
        String value = "";
        for (DDZClient cc : listClients) {
            value += cc.getIdsStr() + "剩" + cc.getListPs().size() + "张牌；";
        }
        notifyAllUser(value);
    }

    private int[] parseToIndex(String msg) {
        try {
            String nMsg = msg.replace("#OUT#", "");
            nMsg = nMsg.replace("#", "");
            // 1, 2, 3, 4,#1@
            String[] split = nMsg.split(",");
            int[] index = new int[split.length];
            for (int i = 0; i < index.length; i++) {
                index[i] = Integer.parseInt(split[i].trim());
            }
            return index;
        } catch (Exception e) {
            return null;
        }
    }


    private void startGame() {
        cntOut = 0;
        prevType = null;
        // 游戏开始前把身份确认
        for (int i = 0; i < listClients.size(); i++) {
            DDZClient client = listClients.get(i);
            client.setGameState(DDZClient.STATE_OUT);
            if (i == canTalkIndex) {
                client.setIds(1);
                client.getListPs().addAll(this.listDZ);
                client.p2RemoteClient();
                client.sendMsgToClient("你是地主，请输入 #OUT#序号1,序号2,序号3...# 开始出牌");
            } else {
                client.setIds(0);
                client.sendMsgToClient("等待地主出牌");
            }
        }
    }


    private void reGo() {
        for (DDZClient client : listClients) {
            client.setListPs(null);
        }
        control = new PControl(); // 得到了新的一副牌
        control.shuffle();

        List<List<P>> deal = control.deal();
        for (int i = 0; i < 3; i++) {
            listClients.get(i).setListPs(deal.get(i));
        }
        this.listDZ = deal.get(3);

        // 发送牌的信息到用户手中
        for (DDZClient client : listClients) {
            client.setGameState(DDZClient.STATE_BOSS); // 客户端状态更改为抢地主状态
            client.p2RemoteClient();
        }
        // 创造地主
        createDZ();
    }

    // 判断准备状态
    private void judgeGoState(DDZClient client) {
        String value = "";
        for (DDZClient c : listClients) {
            if (c.getGameState() != DDZClient.STATE_GO) {
                value += (c.getIPAddr() + "  ");
            }
        }
        if (!value.equals("")) {
            notifyAllUser(value + "用户没有准备");
            return;
        }
        reGo();
    }

    //
    private void createDZ() {
        Random random = new Random();
        int i = random.nextInt(3);
        cntPass = 0;
        canTalkIndex = i;
        canTalkClient = listClients.get(canTalkIndex);
        dzStateMsg(i);
    }

    // 地主状态，发送信息到客户端
    private void dzStateMsg(int i) {
        for (int j = 0; j < listClients.size(); j++) {
            DDZClient client = listClients.get(j);
            if (j == i) {
                client.sendMsgToClient("你是地主，输入#BOSS为抢地主，#PASS不要地主");
            } else {
                client.sendMsgToClient("请等待地主确认");
            }
        }
    }

}
