package com.hp.test.dui.test;

import com.hp.test.dui.client.ClientLand;

/**
 * 客户端
 */
public class TestClient {
    public static void main(String[] args) {
        ClientLand clientLand=new ClientLand("localhost");
        clientLand.connectionServer();
    }
}
