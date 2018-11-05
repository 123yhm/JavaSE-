package com.hp.test.dui.test;

import com.hp.test.dui.server.ServerLand;

/**
 * 服务端开始
 */
public class TestServer {
    public static void main(String[] args) {
        ServerLand serverLand=new ServerLand();
        serverLand.acctextClient();
    }
}
