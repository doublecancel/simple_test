package com.test.test.NIO;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.time.LocalDateTime;

/**
 * Created by Administrator on 2017/7/4.
 */
public class FileIO {
    public static void readByIO(String filePath) throws Exception{
        FileInputStream in = new FileInputStream(new File(filePath));
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        System.out.println("begin-------------------------");
        String a ;
        while ((a = reader.readLine()) != null){
            System.out.println(a);
        }
        System.out.println("end-------------------------");
        in.close();

    }

    public static void readByNio(String filePath) throws Exception {
        FileInputStream in = new FileInputStream(new File(filePath));
        FileChannel channel = in.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //读取到缓冲区
        int a = channel.read(buffer);
        System.out.println("begin-----------------------------");
        while (a != -1){
            buffer.flip();//转换为读模式
            while (buffer.hasRemaining()){
                System.out.print((char)buffer.get());
            }
            buffer.compact();//清空buffer
            a = channel.read(buffer);
        }
        System.out.println("end-----------------------------");
        buffer.clear();
        channel.close();
        in.close();
    }


    public static void writeByNio(String filePath) throws Exception {
        RandomAccessFile file = new RandomAccessFile(filePath, "rw");
        FileChannel channel = file.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(512);
        buffer.put(("当前时间：" + LocalDateTime.now().toString()).getBytes());
        buffer.flip();//转换为
        while (buffer.hasRemaining()){
            channel.write(buffer);
        }
        buffer.compact();
        channel.close();
    }





    public static void main(String[] args) throws Exception{

//        readByIO("D:\\workspace\\github\\simple_test\\test_java\\src\\main\\resources\\test.txt");
//        writeByNio("D:\\workspace\\github\\simple_test\\test_java\\src\\main\\resources\\test.txt");
        readByNio("D:\\workspace\\github\\simple_test\\test_java\\src\\main\\resources\\test.txt");




    }

}
