package com.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {
    /**
     * 此处是80 端口访问8001端口中的服务,所以配置
     */
//    private static final String PAYMENT_URL="http://localhost:8001";
    private static final String INVOKE_URL="http://consul-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/consumer/payment/consul")
    public String orderZk(){
        String result =restTemplate.getForObject(INVOKE_URL+"/payment/consul",String.class);
        return result;
    }
}
