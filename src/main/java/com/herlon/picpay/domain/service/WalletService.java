package com.herlon.picpay.domain.service;

import com.herlon.picpay.domain.entity.Wallet;
import org.springframework.stereotype.Service;

@Service
public interface WalletService {
    Wallet findById(Long id);
    Wallet save(Wallet wallet);
}
