package com.test.config;

import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import com.test.config.handler.SingleKeyDatabaseShardingAlgorithmImpl;
import com.test.config.handler.SingleKeyTableShardingAlgorithmImpl;
import com.test.config.properties.DatasourceProperties;
import com.test.config.properties.DbCreateor;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/8.
 */
@Configuration
@EnableConfigurationProperties(DatasourceProperties.class)
public class ShardingConfig {
    @Autowired
    DatasourceProperties datasourceProperties;
    @Autowired
    ApplicationContext context;
    //----------------------分片总策略
    @Bean
    public ShardingRule shardingRule(DataSourceRule dataSourceRule, DatabaseShardingStrategy databaseShardingStrategy, TableShardingStrategy tableShardingStrategy){
        ShardingRule rule = ShardingRule.builder()
                .dataSourceRule(dataSourceRule)
                .tableRules(tableRules())
//                .databaseShardingStrategy(databaseShardingStrategy)
//                .tableShardingStrategy(tableShardingStrategy)
                .build();
        return rule;
    }

    //-------------------------表策略
    private Collection<TableRule> tableRules(){
        TableRule rule = new TableRule.TableRuleBuilder("user")
                .actualTables(Lists.newArrayList( "test.user_1", "test.user_2", "test.user_3"))
                .tableShardingStrategy(context.getBean(TableShardingStrategy.class))
                .databaseShardingStrategy(context.getBean(DatabaseShardingStrategy.class))
                .dataSourceNames(Lists.newArrayList("test"))
                .build();
        return Lists.newArrayList(rule);
    }
    @Bean
    public TableShardingStrategy tableShardingStrategy(SingleKeyTableShardingAlgorithm tableShardingAlgorithm){
        TableShardingStrategy shardingStrategy = new TableShardingStrategy("id", tableShardingAlgorithm);
        return shardingStrategy;
    }
    @Bean
    public SingleKeyTableShardingAlgorithm<String> tableShardingAlgorithm(){
        SingleKeyTableShardingAlgorithm algorithm = new SingleKeyTableShardingAlgorithmImpl();
        return algorithm;
    }
    //--------------------------库策略
    @Bean
    public DatabaseShardingStrategy databaseShardingStrategy(){
        return new DatabaseShardingStrategy("id", new SingleKeyDatabaseShardingAlgorithmImpl());
    }

    @Bean
    public DataSourceRule dataSourceRule(){
        Map<String, DataSource> map = new HashMap<>();
        map.put("test", DbCreateor.create());
        DataSourceRule rule = new DataSourceRule(map, "test");
        return rule;
    }
//    //-------------------------数据源配置
    @Bean
    public DataSource dataSource(ShardingRule shardingRule){
        return ShardingDataSourceFactory.createDataSource(shardingRule);
    }

//    @Bean
//    public JdbcTemplate jdbcTemplate(DataSource dataSource){
//        return new JdbcTemplate(dataSource);
//    }

    //------------------------------基于spring配置



}
