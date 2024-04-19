package pacifico.davidson.picpaysimplificado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pacifico.davidson.picpaysimplificado.domain.transaction.Transaction;
import pacifico.davidson.picpaysimplificado.domain.transaction.TransactionDTO;
import pacifico.davidson.picpaysimplificado.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transactionDTO) throws Exception {
        var newTransaction = transactionService.createTransaction(transactionDTO);
        return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
    }
}
