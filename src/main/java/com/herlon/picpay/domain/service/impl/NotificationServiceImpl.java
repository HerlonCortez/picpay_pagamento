package com.herlon.picpay.domain.service.impl;

import com.herlon.picpay.domain.entity.Transaction;
import com.herlon.picpay.domain.service.NotificationProducerService;
import com.herlon.picpay.domain.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);
    private final NotificationProducerService notificationProducer;

    @Override
    public void notify(Transaction transaction) {
        LOGGER.info("notifying transaction {}...", transaction);

        notificationProducer.sendNotification(transaction);
    }
}
