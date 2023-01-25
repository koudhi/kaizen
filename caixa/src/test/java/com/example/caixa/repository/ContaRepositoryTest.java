package com.example.caixa.repository;

import com.example.caixa.domain.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Optional;

@DataJpaTest
//@DisplayName("Testes for account")
class ContaRepositoryTest {
    @Autowired
    private AccountRepository accountRepository;

    @Test
    @DisplayName("Save creates account when sucessfull")
    public void save_PersistAnime_WhenSuccessful(){
        Account account = createAccount();
        Account savedAccout = this.accountRepository.save(account);
        Assertions.assertNotNull(savedAccout);
        Assertions.assertNotNull(savedAccout.getAccountNumber());
        Assertions.assertEquals(savedAccout.getSaldo(),new BigDecimal("123.45"));
        Assertions.assertEquals(savedAccout.getTitular(),"Titular Teste");
    }

    @Test
    @DisplayName("Save updates account when sucessfull")
    public void save_UpdateAnime_WhenSuccessful(){
        Account account = createAccount();
        Account savedAccout = this.accountRepository.save(account);
        savedAccout.setSaldo(new BigDecimal("543.21"));
        savedAccout.setTitular("novo nome");
        Account updatedAccount = this.accountRepository.save(savedAccout);
        Assertions.assertNotNull(updatedAccount);
        Assertions.assertEquals(updatedAccount.getAccountNumber(),savedAccout.getAccountNumber());
        Assertions.assertEquals(updatedAccount.getSaldo(),savedAccout.getSaldo());
        Assertions.assertEquals(updatedAccount.getTitular(),savedAccout.getTitular());
    }

    @Test
    @DisplayName("Delete updates account when sucessfull")
    public void save_DeleteAnime_WhenSuccessful(){
        Account account = createAccount();
        Account savedAccout = this.accountRepository.save(account);
        this.accountRepository.delete(savedAccout);
        Optional<Account> optionalAccount = this.accountRepository.findById(savedAccout.getAccountNumber());

        Assertions.assertFalse(optionalAccount.isPresent());
    }


    @Test
    @DisplayName("Find by id returns account when sucessfull")
    public void findById_ListAcc_WhenSuccessful(){
        Account account = createAccount();
        Account savedAccout = this.accountRepository.save(account);
        Optional<Account> optionalAccount = this.accountRepository.findById(savedAccout.getAccountNumber());

        Assertions.assertEquals(optionalAccount, account);
    }

    private Account createAccount(){
        return Account.builder().titular("Titular Teste")
                .saldo(new BigDecimal("123.45"))
                .build();

    }
}