package com.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//自定义Ribbon算法 默认为轮询,对其进行修改
@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule(){
//        return new RoundRobinRule();//默认是轮询
        return new RandomRule();//定义成随机()
    }
}
