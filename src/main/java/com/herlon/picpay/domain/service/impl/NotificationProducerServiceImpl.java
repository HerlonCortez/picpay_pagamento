package com.herlon.picpay.domain.service.impl;

import com.herlon.picpay.domain.entity.Transaction;
import com.herlon.picpay.domain.service.NotificationProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationProducerServiceImpl implements NotificationProducerService {
    private final KafkaTemplate<String, Transaction> kafkaTemplate;

    @Override
    public void sendNotification(Transaction transaction) {
        kafkaTemplate.send("transaction-notification", transaction);
    }
}
