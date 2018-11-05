package com.hp.test.dou.terrace;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 服务端上的客户端
 */
public class ServerClient {
    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public ServerClient(ServerSide serverSide,Socket socket){
        this.socket=socket;
        this.inputStream=getInputStream();
        this.outputStream=getOutputStream();
    }
}
