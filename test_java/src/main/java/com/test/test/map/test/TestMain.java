package com.test.test.map.test;

import java.security.Key;

/**
 * Created by Administrator on 2017/5/10.
 */
public class TestMain {
    public static void main(String[] args) {



        String algorithm = "DES"; // 定义加密算法,可用 DES,DESede,Blowfish
        String message = "Hello World. 这是待加密的信息"; // 生成个DES密钥
        Key key;

        CipherMessage cm = new CipherMessage(algorithm, message);
        key = cm.initKey();
        byte[] msg = cm.CipherMsg();
        System.out.println("加密后的密文为：" + new String(msg));
        // System.out.println("密钥为："+new String(cm.getBinaryKey(key)));


        System.out.println(cm.getBinaryKey(key));
        System.out.println("解密密文为：" + cm.EncipherMsg(msg, key));
    }
}
