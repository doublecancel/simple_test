package cn.guice.demo;

import cn.guice.demo.service.CommonInvoke;
import cn.guice.demo.service.ConstantsService;
import cn.guice.demo.service.Player;
import com.google.inject.*;

import javax.inject.Named;

/**
 * Created by Administrator on 2017/5/26.
 */
@Singleton
public class PlayerClient {

    @Inject
    @Named("good")
    Player player;

    void test(){
        player.bat();
    }

    public static void main(String[] args) {

        PlayerModule module = new PlayerModule("广州");
        Injector injector = Guice.createInjector(new Module[]{module});
        PlayerClient client = injector.getInstance(PlayerClient.class);
        PlayerClient client1 = injector.getInstance(PlayerClient.class);
        ConstantsService service = injector.getInstance(ConstantsService.class);
        service.testConstant();

//        System.out.println(client == client1);
//        client.test();

        CommonInvoke invoke = injector.getInstance(CommonInvoke.class);
        invoke.test();

//        invoke.ok(null, "a");

    }
}
