package com.herlon.picpay.domain.service;

import com.herlon.picpay.domain.entity.Transaction;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public interface NotificationProducerService {
    void sendNotification(Transaction transaction);
}
