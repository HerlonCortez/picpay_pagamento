package com.herlon.picpay.controller;

import com.herlon.picpay.domain.entity.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transaction")
public interface TransactionController {
    @GetMapping
    List<Transaction> list();

    @PostMapping
    ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction);
}
