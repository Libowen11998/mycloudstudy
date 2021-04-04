package com.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")//全局fallback（兜底方法）
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result= paymentHystrixService.paymentInfo_OK(id);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentTimeOutFallBackMethod",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//    })
    @HystrixCommand
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        String result= paymentHystrixService.paymentInfo_TimeOut(id);
        return result;
    }

    //上边方法出现错误之后，使用此方法进行一个声明
    public String paymentTimeOutFallBackMethod(@PathVariable("id") Integer id){
        return "我是消费者80，对方支付系统繁忙，请稍后再试，o(╥﹏╥)o";
    }

    //此处定义全局fallback方法 （同样也是兜底方法）
    public String payment_Global_FallbackMethod(){
        return "Global异常处理信息，请稍后再试，/(T o T)/~~";
    }

    //===============服务熔断
    @GetMapping("/consumer/payment/circuit/{id}")
    public String critu(@PathVariable("id") Integer id){
        String result =paymentHystrixService.paymentCircuitBreaker(id);
        return result;
    }
}