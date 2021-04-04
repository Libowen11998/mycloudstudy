package com.springcloud.service;

import org.apache.ibatis.annotations.Param;
import com.springcloud.entities.Payment;

public interface PaymentService {
    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
