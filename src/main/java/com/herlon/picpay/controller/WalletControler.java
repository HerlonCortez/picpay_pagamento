package com.herlon.picpay.controller;

import com.herlon.picpay.domain.entity.Wallet;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("wallet")
public interface WalletControler {
    @PostMapping
    ResponseEntity<Wallet> create(@RequestBody Wallet wallet);

    @GetMapping("/{id}")
    ResponseEntity<Wallet> listar(@PathVariable Long id);
}
