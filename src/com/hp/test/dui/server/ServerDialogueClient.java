package com.hp.test.dui.server;




import com.hp.test.dui.action.PlayingCard;
import com.hp.test.dui.client.ClientLand;
import com.hp.test.dui.util.UtilLand;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

/**
 * 负责发送消息
 */
public class ServerDialogueClient {
    public static final int STATE_JOIN = 0;
    public static final int STATE_GO = 1;
    public static final int STATE_BOSS = 2;
    public static final int STATE_OUT = 3;


    private ServerLand server;
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    //用户手中的牌
     private List<PlayingCard> listUser;

    // 游戏状态
    // 0 表示加入状态
    // 1 表示用户准备状态
    // 2 表示谁先来状态
    // 3 表示可以出牌的状态
    private int gameState = ServerDialogueClient.STATE_JOIN;
    //身份
    private int identity;

    public ServerDialogueClient(ServerLand server,Socket socket){
        try {
            this.server=server;
            this.socket=socket;
            inputStream=this.socket.getInputStream();
            outputStream=this.socket.getOutputStream();
            new Thread(accept).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //发送消息到客户端
    public void sendClientMag(String msg){
        try {
            outputStream.write(msg.getBytes());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //接收客户端消息
    private Runnable accept=new Runnable() {
        @Override
        public void run() {
            try {
                while (inputStream != null) {
                    byte[] bytes = new byte[1024];
                    int read = inputStream.read(bytes);
                    if (read < 0) {
                        break;
                    }
                    String str=new String(bytes, 0, read);
                    server.startInputMsg(ServerDialogueClient.this, str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
    public String getIPAddr() {//返回没有准备用户的ip地址
        if (this.socket != null) {
            return this.socket.getInetAddress().getHostAddress();
        }
        return "";
    }
    //发送牌信息向客户端
    public void playingCareInterMsg(){
        if (listUser==null){
            return ;
        }
        UtilLand.sortPlayingCare(listUser);
        String str="";
        for (int i = 0; i < listUser.size(); i++) {
            PlayingCard playingCard = listUser.get(i);
            str+=(playingCard.plyingCareShow()+"("+i+")");
        }
        sendClientMag(str);
    }

    public List<PlayingCard> getListUser() {
        return listUser;
    }

    public void setListUser(List<PlayingCard> listUser) {
        this.listUser = listUser;
    }

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public int getIdentity() {
        return identity;
    }
    public String getIdentityStr() {
        return identity== 1 ? "小明":"小红";
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }
}
