package cn.guice.demo;

import cn.guice.demo.service.GoodPlayer;
import com.google.inject.Provider;


/**
 * Created by Administrator on 2017/5/26.
 */
public class PlayerProvider implements Provider<GoodPlayer> {

    @Override
    public GoodPlayer get() {

        System.out.println("init bean");

        return new GoodPlayer();
    }
}
