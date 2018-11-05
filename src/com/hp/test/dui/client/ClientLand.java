package com.hp.test.dui.client;



import com.hp.test.dui.action.ActionLand;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.rmi.server.LogStream;
import java.util.List;
import java.util.Scanner;

public class ClientLand {



    private static final int port = 1;
    private String clientip;
    private Socket socket;
    private  InputStream inputStream;
    private OutputStream outputStream;



   public ClientLand(String clientip) {
        this.clientip = clientip;
    }
    //连接服务
    public  void connectionServer(){
        try {
            this.socket=new Socket(this.clientip,port);
            this.inputStream=this.socket.getInputStream();
            this.outputStream=this.socket.getOutputStream();
            new Thread(read).start();
            new Thread(output).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //读取服务器消息
    private  Runnable read=new Runnable(){
        @Override
        public void run() {
            try {
                while (inputStream != null) {
                    byte[] buf = new byte[1024];
                    int read = inputStream.read(buf);
                    if (read < 0) {
                        break;
                    }
                    System.out.println(new String(buf, 0, read));
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    };

    //发送客户端消息
    private  Runnable output=new Runnable(){
        @Override
        public void run() {
            Scanner scanner = new Scanner(System.in);
            try {
                while (outputStream != null) {
                    String next = null;
                    while (true) {
                        next = scanner.next(); // 得到用户输入的信息
                        if (ActionLand.judgeInput(next)) {
                            break;
                        } else {
                            System.out.println("输入的不合法，请重新输入");
                        }

                    }
                    outputStream.write(next.getBytes());
                    outputStream.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

}
