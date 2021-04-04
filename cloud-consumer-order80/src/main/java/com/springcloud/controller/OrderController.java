package com.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
    private static final String INVOKE_URL="http://CLOUD-PROVIDER-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    /**
     * 此处的postForObject()方法
     * 三个参数
     * 1:是端口号/方法名
     * 2:传入的数据
     * 3:返回的数据
     * @param payment
     * @return
     */
    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(INVOKE_URL+"/payment/create",payment, CommonResult.class);
    }
    //上边访问的是一个服务中的创建方法
    //下边访问的是一个服务中的查询方法
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(INVOKE_URL+"/payment/get/"+id,CommonResult.class);
    }

    @GetMapping("/consumer/payment/getEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> entity =restTemplate.getForEntity(INVOKE_URL+"/payment/get/"+id,CommonResult.class);
        if(entity.getStatusCode().is2xxSuccessful()){
            log.info(entity.getStatusCode()+"\t"+entity.getHeaders());
            return entity.getBody();
        }else {
            return new CommonResult<>(444,"查询失败");
        }
    }
}
