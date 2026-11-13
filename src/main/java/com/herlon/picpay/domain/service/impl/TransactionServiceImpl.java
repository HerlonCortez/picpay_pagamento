package com.herlon.picpay.domain.service.impl;

import com.herlon.picpay.domain.entity.Transaction;
import com.herlon.picpay.domain.entity.Wallet;
import com.herlon.picpay.domain.enums.WalletType;
import com.herlon.picpay.domain.exception.InvalidTransactionException;
import com.herlon.picpay.domain.service.AuthorizerService;
import com.herlon.picpay.domain.service.NotificationService;
import com.herlon.picpay.domain.service.TransactionService;
import com.herlon.picpay.domain.service.WalletService;
import com.herlon.picpay.infrastructure.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);
    private final TransactionRepository transactionRepository;
    private final WalletService walletService;
    private final AuthorizerService authorizerService;
    private final NotificationService notificationService;

    @Override
    @Transactional
    public synchronized Transaction create(Transaction transaction) {
        validate(transaction);

        // 1. Verifica se a transferência está autorizada
        //authorizerService.authorize(transaction);

        // 2. Busca as carteiras
        var walletPayer = walletService.findById(transaction.getPayer());
        var walletPayee = walletService.findById(transaction.getPayee());

        // 3. Atualiza os saldos
        walletPayer.debit(transaction.getAmount());
        walletPayee.credit(transaction.getAmount());

        // 4. Persiste as alterações
        walletService.save(walletPayer);
        walletService.save(walletPayee);

        // 5. Salva a transação
        var newTransaction = transactionRepository.save(transaction);

        // 6. Envia a notificação
        notificationService.notify(newTransaction);

        return newTransaction;
    }

    private void validate(Transaction transaction) {

        LOGGER.info("validating transaction {}...", transaction);

        Wallet payee = walletService.findById(transaction.getPayee());

        Wallet payer = walletService.findById(transaction.getPayer());

        boolean isCommonWallet =
                payer.getType() == WalletType.COMUM.getValue();

        boolean hasSufficientBalance =
                payer.getBalance().compareTo(transaction.getAmount()) >= 0;

        boolean isNotPayingToSelf =
                !payer.getId().equals(payee.getId());

        if (!isCommonWallet || !hasSufficientBalance || !isNotPayingToSelf) {
            throw new InvalidTransactionException(
                    "Invalid transaction - " + transaction);
        }
    }

    public List<Transaction> list() {
        return transactionRepository.findAll();
    }
}
