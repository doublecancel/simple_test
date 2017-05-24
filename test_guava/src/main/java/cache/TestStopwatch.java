package cache;

import com.google.common.base.Stopwatch;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by Administrator on 2017/5/11.
 */
public class TestStopwatch {


    public static void main(String[] args) {


        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopwatch.stop();
        long time = stopwatch.elapsed(SECONDS);
        System.out.println("time:" + time);

    }


}
