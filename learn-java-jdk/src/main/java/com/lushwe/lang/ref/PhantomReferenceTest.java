package com.lushwe.lang.ref;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 说明：虚引用测试
 *
 * @author Jack Liu
 * @date 2020-09-10 10:18
 * @since 0.1
 */
public class PhantomReferenceTest {

    public static void main(String[] args) {

        testPhantomReference();

    }

    /**
     * 虚引用
     */
    private static void testPhantomReference() {
        ReferenceQueue queue = new ReferenceQueue();
        PhantomReference<byte[]> phantomReference = new PhantomReference<byte[]>(new byte[1], queue);

        // 无法通过虚引用获得对象，所以返回null
        System.out.println(phantomReference.get());

        new Thread(() -> {
            List<byte[]> bytes = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                bytes.add(new byte[1024 * 1024]);
            }
        }).start();

        new Thread(() -> {
            while (true) {
                Reference poll = queue.poll();
                if (poll != null) {
                    System.out.println("虚引用被回收了：" + poll);
                }
            }
        }).start();

        Scanner scanner = new Scanner(System.in);
        scanner.hasNext();
    }
}
