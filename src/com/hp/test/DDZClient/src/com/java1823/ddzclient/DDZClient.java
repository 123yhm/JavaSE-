package com.hp.test.DDZClient.src.com.java1823.ddzclient;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class DDZClient {

    private final int port = 9999;       // 端口
    private String addr;    // ip地址

    private Socket socket;
    private InputStream is;     // 输入流
    private OutputStream os;    // 输出流

    public DDZClient(String addr) {
        this.addr = addr;
    }

    public void connet() {
        try {
            this.socket = new Socket(this.addr, port);
            this.is = this.socket.getInputStream();
            this.os = this.socket.getOutputStream();
            new Thread(read).start();
            new Thread(write).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private Runnable read = new Runnable() {
        @Override
        public void run() {
            try {
                while (is != null) {
                    byte[] buf = new byte[1024];
                    int read = is.read(buf);
                    if (read < 0) {
                        break;
                    }
                    System.out.println(new String(buf, 0, read));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    private Runnable write = new Runnable() {
        @Override
        public void run() {
            Scanner scanner = new Scanner(System.in);
            try {
                while (os != null) {
                    String next = null;
                    while (true) {
                        next = scanner.next(); // 得到用户输入的信息
                        if (Action.judgeInput(next)) {
                            break;
                        } else {
                            System.out.println("输入的不合法，请重新输入");
                        }
                    }
                    os.write(next.getBytes());
                    os.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
}
