package config;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;

/**
 * Created by Administrator on 2017/4/19.
 */
@DisconfFile(filename = "static.properties")
public class StaticConfig {


    private static int var;
    @DisconfFileItem(associateField = "var", name = "static.var")
    public static void setVar(int var) {
        StaticConfig.var = var;
    }
}
