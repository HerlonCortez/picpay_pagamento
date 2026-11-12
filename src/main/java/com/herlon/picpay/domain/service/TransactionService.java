package com.herlon.picpay.domain.service;

import com.herlon.picpay.domain.entity.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {
    Transaction create(Transaction transaction);
    List<Transaction> list();
}
