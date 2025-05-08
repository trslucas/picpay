package com.picpaysimplificado.controllers;

import com.picpaysimplificado.domain.transaction.Transaction;
import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.dtos.TransactionDTO;
import com.picpaysimplificado.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transactionDTO) throws Exception {

        SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        Transaction newTransaction = this.transactionService.createTransaction(transactionDTO);

        return new ResponseEntity<>(newTransaction, HttpStatus.OK);


    }


    @GetMapping()
    public ResponseEntity <List<Transaction>> listAllTransactionsBySenderId() throws Exception {

        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UUID senderId = authenticatedUser.getId();


        List<Transaction> transactions = this.transactionService.listAllTransactionsByUser(senderId);

        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

}
