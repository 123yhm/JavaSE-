package com.hp.test.DDZ.src.com.java1823.ddz;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

// 在服务端的客户端对象
public class DDZClient {

    public static final int STATE_JOIN = 0;
    public static final int STATE_GO = 1;
    public static final int STATE_BOSS = 2;
    public static final int STATE_OUT = 3;

    private DDZServer server;
    private Socket socket;
    private OutputStream os;
    private InputStream is;

    // 游戏状态
    // 0 表示加入状态
    // 1 表示用户准备状态
    // 2 表示抢地主状态
    // 3 表示可以出牌的状态
    private int gameState = DDZClient.STATE_JOIN;

    // 表示身份
    // 0表示农民
    // 1表示地主
    private int ids;

    // 用户手中的牌
    private List<P> listPs;

    public DDZClient(DDZServer server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            os = this.socket.getOutputStream();
            is = this.socket.getInputStream();
            new Thread(getMsg).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 服务端发送信息给到客户端
    public void sendMsgToClient(String msg) {
        try {
            os.write(msg.getBytes());
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 服务端需要接收客户端的信息
    private Runnable getMsg = new Runnable() {
        @Override
        public void run() {
            try {
                while (is != null) {
                    byte[] buf = new byte[1024];
                    int read = is.read(buf);
                    String clientMsg = new String(buf, 0, read);
                    // 接收到客户端发送过来的消息，交给服务端对象去处理
                    server.execCommand(DDZClient.this, clientMsg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    // 将手中的牌信息发送到客户端
    public void p2RemoteClient() {
        if (listPs == null) {
            return;
        }
        PUtil.sortP(listPs);
        String msg = "";
        for (int i = 0; i < listPs.size(); i++) {
            P p = listPs.get(i);
            msg += (p.show() + "(" + i + "),");
        }
        sendMsgToClient(msg);
    }

    public String getIPAddr() {
        if (this.socket != null) {
            return this.socket.getInetAddress().getHostAddress();
        }
        return "";
    }

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public List<P> getListPs() {
        return listPs;
    }

    public void setListPs(List<P> listPs) {
        this.listPs = listPs;
    }

    public int getIds() {
        return ids;
    }

    public String getIdsStr() {
        return ids == 1 ? "地主" : "农民";
    }

    public void setIds(int ids) {
        this.ids = ids;
    }
}
