package com.test.config.handler;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;

import java.util.Collection;

/**
 * Created by Administrator on 2017/6/8.
 */
public class SingleKeyTableShardingAlgorithmImpl implements SingleKeyTableShardingAlgorithm<Integer> {


    private int count = 3;


    @Override
    public String doEqualSharding(Collection<String> availableTargetNames, ShardingValue<Integer> shardingValue) {

        String eq = shardingValue.getValue() + "";
        String result =  availableTargetNames.stream().filter((a) -> {
            return a.endsWith(eq);
        }).limit(1).findAny().orElseThrow(() -> {
            return new UnsupportedOperationException();
        });
        return result;
    }





    @Override
    public Collection<String> doInSharding(Collection<String> availableTargetNames, ShardingValue<Integer> shardingValue) {
        return null;
    }

    @Override
    public Collection<String> doBetweenSharding(Collection<String> availableTargetNames, ShardingValue<Integer> shardingValue) {
        return null;
    }
}
