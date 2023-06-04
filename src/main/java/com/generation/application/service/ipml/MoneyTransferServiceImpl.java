package com.generation.application.service.ipml;

import com.generation.application.model.Role;
import com.generation.application.service.MoneyTransferService;
import com.generation.application.service.OrderService;
import com.generation.application.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class MoneyTransferServiceImpl implements MoneyTransferService {
    private final UserService userService;
    private final OrderService orderService;
    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void transfer(Integer userId, Integer orderId) {
        var balance = userService.findById(userId).balance();
        var cost= orderService.findById(orderId)
                .orderDetails()
                .cost();
        checkCorrect(cost,balance);
        userService.updateUserBalance(userId,balance.subtract(cost));

        var owner = userService.findByRole(Role.OWNER);
        userService.updateUserBalance(owner.getId(),
                owner.getBalance().add(cost));
        //TODO: changed order status PAID
    }
    private void checkCorrect(BigDecimal balance, BigDecimal cost){
        if((balance.compareTo(cost)>0)){
            throw new IllegalStateException("You don't have enough money");
        }
    }
}
