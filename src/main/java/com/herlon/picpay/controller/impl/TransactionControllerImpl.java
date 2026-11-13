package com.herlon.picpay.controller.impl;

import com.herlon.picpay.controller.TransactionController;
import com.herlon.picpay.domain.entity.Transaction;
import com.herlon.picpay.domain.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TransactionControllerImpl implements TransactionController {
    private final TransactionService transactionService;


    @Override
    public List<Transaction> list() {
        return transactionService.list();
    }

    @Override
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        return ResponseEntity.ok(transactionService.create(transaction));
    }
}
