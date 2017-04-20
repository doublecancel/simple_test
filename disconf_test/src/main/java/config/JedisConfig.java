package config;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/4/19.
 */
@DisconfFile(filename = "redis.properties")
@Service
public class JedisConfig {
    private String host;
    private int port;
    @DisconfFileItem(name = "redis.host", associateField = "host")
    public void setHost(String host) {
        this.host = host;
    }
    @DisconfFileItem(name = "redis.port", associateField = "port")
    public void setPort(int port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }



    public int getPort() {
        return port;
    }


}
