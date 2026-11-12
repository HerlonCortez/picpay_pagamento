package com.herlon.picpay.controller.impl;

import com.herlon.picpay.controller.WalletControler;
import com.herlon.picpay.domain.entity.Wallet;
import com.herlon.picpay.domain.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WalletControllerImpl implements WalletControler {
    private final WalletService walletService;

    @Override
    public ResponseEntity<Wallet> create(@RequestBody Wallet wallet){
        return ResponseEntity.ok(walletService.save(wallet));
    }

    @Override
    public ResponseEntity<Wallet> listar(@PathVariable Long id) {
        return ResponseEntity.ok(walletService.findById(id));
    }
}
