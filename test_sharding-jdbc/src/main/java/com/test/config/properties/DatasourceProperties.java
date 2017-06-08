package com.test.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Administrator on 2017/6/8.
 */
@ConfigurationProperties(prefix = "datasource")
@Data
public class DatasourceProperties {
    private String driverClass;
    private String url;
    private String user;
    private String password;
}
