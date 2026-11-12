package com.herlon.picpay.domain.service.impl;

import com.herlon.picpay.domain.entity.Notification;
import com.herlon.picpay.domain.entity.Transaction;
import com.herlon.picpay.domain.exception.NotificationException;
import com.herlon.picpay.domain.service.NotificationConsumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class NotificationConsumerServiceImpl
        implements NotificationConsumerService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(NotificationConsumerService.class);

    private final RestClient restClient;

    public NotificationConsumerServiceImpl(
            @Qualifier("notificationRestClient")
            RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    @KafkaListener(
            topics = "transaction-notification",
            groupId = "picpay-desafio-backend"
    )
    public void receiveNotification(Transaction transaction) {

        LOGGER.info("notifying transaction {}...", transaction);

        var response = restClient.post()
                .body(transaction)
                .retrieve()
                .toEntity(Notification.class);

        if (response.getStatusCode().isError()
                || response.getBody() == null
                || !response.getBody().message()) {

            throw new NotificationException(
                    "Error notifying transaction " + transaction
            );
        }

        LOGGER.info(
                "notification has been sent {}...",
                response.getBody()
        );
    }
}
