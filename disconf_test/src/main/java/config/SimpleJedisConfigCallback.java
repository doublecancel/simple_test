package config;

import com.baidu.disconf.client.common.annotations.DisconfUpdateService;
import com.baidu.disconf.client.common.update.IDisconfUpdate;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/4/19.
 */
@Service
@Scope("singleton")
@DisconfUpdateService(classes = {JedisConfig.class})
public class SimpleJedisConfigCallback implements IDisconfUpdate{
    @Override
    public void reload() throws Exception {
        System.out.println( "reload .........................." );
    }
}
