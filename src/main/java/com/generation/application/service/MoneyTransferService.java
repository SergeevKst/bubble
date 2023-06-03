package com.generation.application.service;

import com.generation.application.entity.User;

public interface MoneyTransferService {
    void transfer(Integer userId, Integer orderId);
}
