package com.lushwe.io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 说明：客户端
 *
 * @author Jack Liu
 * @date 2020-08-27 09:51
 * @since 0.1
 */
public class BIOClient {

    public static void main(String[] args) {

        new BIOClient().conn("127.0.0.1", 8888, "我是客户端");

    }

    private void conn(String ip, int port, String message) {

        OutputStream os = null;
        InputStream is = null;
        try {
            Socket socket = new Socket(ip, port);

            os = socket.getOutputStream();
            os.write(message.getBytes());
            socket.shutdownOutput();

            is = socket.getInputStream();
            int n = -1;
            byte[] buffer = new byte[128];
            while ((n = is.read(buffer)) != -1) {
                System.out.println("客户端接收信息: " + new String(buffer, 0, n));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
