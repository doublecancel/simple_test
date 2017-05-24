package com.test.test.time;

import java.time.Instant;
import java.time.ZoneOffset;

/**
 * Created by Administrator on 2017/5/24.
 */
public class TestTime {

    public static void main(String[] args) {


        Instant instant = Instant.now();
        System.out.println(instant.getNano());


        Instant.now().atOffset(ZoneOffset.ofHours(8));









    }
}
