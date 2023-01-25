package com.example.caixa.service;

import com.example.caixa.domain.Account;
import com.example.caixa.repository.AccountRepository;
import com.example.caixa.request.DepositRequestBody;
import com.example.caixa.request.PostContaRequestBody;
import com.example.caixa.request.TransferRequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import com.example.caixa.mapper.ContaMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
@Log4j2
public class CaixaService {
    private final AccountRepository accountRepository;

    public List<Account> findAllContas(){
        return accountRepository.findAll();
    }

    public Account newAccount(PostContaRequestBody postContaRequestBody) {
        Account novaAccount = ContaMapper.INSTANCE.toConta(postContaRequestBody);
        log.info("postContaRequestBody {}",postContaRequestBody);
        log.info("novaConta {}", novaAccount);

        return accountRepository.save(novaAccount);
    }

    public Account findByConta(long conta) {
        return accountRepository.findById(conta)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"Account not found"));
    }

    public void deposit(Object request){
        System.out.println(request);
    }

    public void saldoIncrement(DepositRequestBody depositRequestBody) {
        long accountNumber = depositRequestBody.getAccountNumber();
        BigDecimal value = depositRequestBody.getValue();
        Account account = accountRepository.findById(accountNumber)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"Account not found"));
        account.setSaldo(account.getSaldo().add(value));
        log.info("novo saldo {}",account.getSaldo());
        accountRepository.save(account);
    }

    public void withdraw(DepositRequestBody depositRequestBody) {
        long accountNumber = depositRequestBody.getAccountNumber();
        BigDecimal value = depositRequestBody.getValue();
        Account account = findByConta(accountNumber);
        accountRepository.save(account);
    }

    public void deleteAccount(long accountNumber) {
        accountRepository.delete(findByConta(accountNumber));
    }

    public void transfer(TransferRequestBody transferRequestBody) {
        long destinataryAccountNumber = transferRequestBody.getDestinataryAccountNumber();
        long accountNumber = transferRequestBody.getAccountNumber();
        BigDecimal value = transferRequestBody.getValue();
        Account account = findByConta(accountNumber);
        Account destinatary = findByConta(destinataryAccountNumber);
        account.setSaldo(account.getSaldo().subtract(value));
        destinatary.setSaldo(destinatary.getSaldo().add(value));
        accountRepository.save(account);
        accountRepository.save(destinatary);

    }
}