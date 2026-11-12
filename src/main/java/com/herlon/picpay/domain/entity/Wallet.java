package com.herlon.picpay.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String cpf;
    private String email;
    private String password;
    int type;
    BigDecimal balance;
    @Version
    Long version;

    public void debit(BigDecimal value){
        this.balance = this.balance.subtract(value);
    }

    public void credit(BigDecimal value){
        this.balance = this.balance.add(value);
    }
}
