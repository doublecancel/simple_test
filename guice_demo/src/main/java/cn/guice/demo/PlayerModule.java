package cn.guice.demo;

import cn.guice.demo.annotation.Address;
import cn.guice.demo.annotation.MethodAop;
import cn.guice.demo.service.BadPlayer;
import cn.guice.demo.service.Player;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;

import java.util.Properties;

/**
 * Created by Administrator on 2017/5/26.
 */
public class PlayerModule implements Module {

    private String address;


    public PlayerModule(String address){
        this.address = address;
    }


    @Override
    public void configure(Binder binder) {
//        binder.bind(Player.class).annotatedWith(Good.class).to(
//                GoodPlayer.class);
//        binder.bind(Player.class).annotatedWith(Bad.class).to(
//                BadPlayer.class);

        binder.bind(Player.class).annotatedWith(Names.named("good")).toProvider(PlayerProvider.class);
        binder.bind(Player.class).annotatedWith(Names.named("bad")).to(BadPlayer.class);

        binder.bindInterceptor(Matchers.any(), Matchers.annotatedWith(MethodAop.class), new MethodBlocker());

        binder.bindConstant().annotatedWith(Address.class).to(address);


        Properties p = new Properties();
        p.setProperty("name", "lxl");
        p.setProperty("age", "25");
        Names.bindProperties(binder, p);

    }
}
