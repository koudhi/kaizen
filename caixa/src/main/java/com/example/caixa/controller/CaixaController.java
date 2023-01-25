package com.example.caixa.controller;

import com.example.caixa.domain.Account;
import com.example.caixa.request.DepositRequestBody;
import com.example.caixa.request.PostContaRequestBody;
import com.example.caixa.request.TransferRequestBody;
import com.example.caixa.service.CaixaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/caixa")
@RequiredArgsConstructor
public class CaixaController {

    private final CaixaService caixaService;

    @GetMapping
    public List<Account> findAllContas(){
        return caixaService.findAllContas();
    }
    @GetMapping("/{conta}")
    public Account findByConta(@PathVariable long conta){
        return caixaService.findByConta(conta);
    }

    @PostMapping("/newAccount")
    public Account newAccount( @Valid @RequestBody PostContaRequestBody postContaRequestBody){
        return caixaService.newAccount(postContaRequestBody);
    }

    @PutMapping("/deposit")
    public void saldoIncrement(@Valid @RequestBody DepositRequestBody depositRequestBody){
        caixaService.saldoIncrement(depositRequestBody);
    }

    @PutMapping("/withdraw")
    public void withdraw(@Valid @RequestBody DepositRequestBody depositRequestBody){
        caixaService.withdraw(depositRequestBody);
    }

    @PutMapping("/transfer")
    public void transfer(@Valid @RequestBody TransferRequestBody transferRequestBody){
        caixaService.transfer(transferRequestBody);
    }

    @DeleteMapping("/{accountNumber}")
    public void deleteAcount(@PathVariable long accountNumber){
        caixaService.deleteAccount(accountNumber);
    }



}
