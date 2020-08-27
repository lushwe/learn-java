package com.lushwe.io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 说明：BIO服务端
 *
 * @author Jack Liu
 * @date 2020-08-27 09:51
 * @since 0.1
 */
public class BIOServer {

    public static void main(String[] args) {

        new BIOServer().start(8888);

    }


    private void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket accept = null;
                try {
                    accept = serverSocket.accept();
                    doAccept(accept);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (accept != null) {
                        accept.close();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doAccept(Socket accept) {
        InputStream is = null;
        OutputStream os = null;
        try {
            // 读
            is = accept.getInputStream();
            byte[] buffer = new byte[128];
            int n = -1;
            while ((n = is.read(buffer)) != -1) {
                System.out.println("服务端接收信息: " + new String(buffer, 0, n));
            }
            // 写
            os = accept.getOutputStream();
            os.write("我是服务端".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
