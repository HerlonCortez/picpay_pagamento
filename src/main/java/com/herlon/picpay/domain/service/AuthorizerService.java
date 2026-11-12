package com.herlon.picpay.domain.service;

import com.herlon.picpay.domain.entity.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public interface AuthorizerService {
    void authorize(Transaction transaction);
}
