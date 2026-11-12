package com.herlon.picpay.domain.service;

import com.herlon.picpay.domain.entity.Transaction;
import org.springframework.stereotype.Service;

@Service
public interface NotificationConsumerService {
    void receiveNotification(Transaction transaction);
}
