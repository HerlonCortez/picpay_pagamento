package com.herlon.picpay.domain.service.impl;

import com.herlon.picpay.domain.entity.Wallet;
import com.herlon.picpay.domain.exception.RecursoNaoEncontradoException;
import com.herlon.picpay.domain.service.WalletService;
import com.herlon.picpay.infrastructure.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;

    @Override
    public Wallet findById(Long id) {
        return walletRepository.findById(id).orElseThrow(
                () -> new RecursoNaoEncontradoException("Carteira não encontrada")
       );
    }

    @Override
    public Wallet save(Wallet wallet) {
        return walletRepository.save(wallet);
    }
}
