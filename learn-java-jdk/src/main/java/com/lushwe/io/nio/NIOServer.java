package com.lushwe.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 说明：NIO服务端
 *
 * @author Jack Liu
 * @date 2019-08-17 20:08
 * @since 0.1
 */
public class NIOServer {


    public static void main(String[] args) throws IOException {

        // 1
        ServerSocketChannel server = ServerSocketChannel.open();

        // 2
        server.bind(new InetSocketAddress(8888));
        server.configureBlocking(false);

        // 3
        Selector selector = Selector.open();

        // 4
        server.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {

            // select方法会阻塞
            selector.select();

            Set<SelectionKey> keys = selector.selectedKeys();

            Iterator<SelectionKey> iterator = keys.iterator();

            while (iterator.hasNext()) {

                SelectionKey key = iterator.next();

                iterator.remove();

                process(selector, key);

            }
        }
    }

    private static void process(Selector selector, SelectionKey key) throws IOException {

        if (key.isAcceptable()) {

            ServerSocketChannel server = (ServerSocketChannel) key.channel();

            SocketChannel channel = server.accept();

            channel.configureBlocking(false);

            channel.register(selector, SelectionKey.OP_READ);

        } else if (key.isReadable()) {

            SocketChannel channel = (SocketChannel) key.channel();

            ByteBuffer buffer = ByteBuffer.allocate(1024);

            int len = channel.read(buffer);

            if (len > 0) {

                buffer.flip();

                String content = new String(buffer.array(), 0, len);

                key = channel.register(selector, SelectionKey.OP_WRITE);

                key.attach(content);

                System.out.println("读取的内容：" + content);
            }

        } else if (key.isWritable()) {

            SocketChannel channel = (SocketChannel) key.channel();

            String content = (String) key.attachment();

            channel.write(ByteBuffer.wrap(("俺是NIO服务端, 成功接收您的信息: " + content).getBytes()));

            channel.close();

        }

    }
}
