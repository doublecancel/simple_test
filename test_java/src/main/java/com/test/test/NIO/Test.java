package com.test.test.NIO;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Administrator on 2017/6/22.
 */
public class Test {
    public static void main(String[] args) throws Exception {



        testFileChannel();



    }



    public static void testFileIO() throws  Exception{

        FileInputStream in = new FileInputStream(
                new File("D:\\workspace\\github\\simple_test\\test_java\\src\\main\\resources\\test.txt"));

        byte[] data = new byte[10];
        StringBuffer sb = new StringBuffer();
        while (in.read(data) != -1){

            sb.append(in.read(data));
        }
        System.out.println(sb.toString());
    }
    public static void testFileChannel() throws Exception {
        FileInputStream in = new FileInputStream(
                new File("D:\\workspace\\github\\simple_test\\test_java\\src\\main\\resources\\test.txt"));
        FileChannel fc = in.getChannel();//获取通道
        ByteBuffer bb = ByteBuffer.allocate(512);//分配缓冲区空间
        int read = fc.read(bb);//读取文件，将文件读取到缓冲区中
        System.out.println(read);
        while (read != -1){
            bb.flip();//开始从缓冲区中读取
            while (bb.hasRemaining()){
                System.out.println(bb.get());
            }
            bb.compact();
            read = fc.read(bb);
        }



    }
}
