package com.hp.test.dou.terrace;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 服务端
 */
public class ServerSide {
    private final int port=1;
    private ServerSocket serverSocket;
    private List<ServerClient> serverClients;
    //客户端集合
    public ServerSide() {
        try {
            this.serverClients = new ArrayList<>();
            this.serverSocket = new ServerSocket(port);//指向当前端口
        } catch (IOException e) {
            this.serverSocket=null;
            e.printStackTrace();
        }
    }
    //建立监听器
    public void accept(){
        System.out.println("服务器已启动-----------");
        while (serverClients.size()<3){
            try {
                Socket accept=serverSocket.accept();
                ServerClient serverClient=new ServerClient(this,accept);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
