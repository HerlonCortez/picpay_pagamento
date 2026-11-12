package com.herlon.picpay.infrastructure.repository;

import com.herlon.picpay.domain.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
