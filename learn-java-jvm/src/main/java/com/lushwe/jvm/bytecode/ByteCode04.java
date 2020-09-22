package com.lushwe.jvm.bytecode;

/**
 * 说明：查看普通方法字节码指令
 *
 * @author Jack Liu
 * @date 2020-09-22 10:32
 * @since 0.1
 */
public class ByteCode04 {

    public static void main(String[] args) {
        ByteCode04 byteCode = new ByteCode04();
        byteCode.run(1);
    }

    public void run(int i) {
        System.out.println(i);
    }
}
