package pacifico.davidson.picpaysimplificado.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pacifico.davidson.picpaysimplificado.domain.transaction.Transaction;
import pacifico.davidson.picpaysimplificado.domain.transaction.TransactionDTO;
import pacifico.davidson.picpaysimplificado.repositories.TransactionRepository;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private NotificationService notificationService;

    public Transaction createTransaction(TransactionDTO transaction) throws Exception {
        var payer = userService.findUserById(transaction.payerId());
        var payee = userService.findUserById(transaction.payeeId());

        userService.validateUser(payer, transaction.amount());

        boolean isAuthorize = authorizeTransaction();
        if(!isAuthorize){
            throw new Exception("Transação não autorizada.");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmout(transaction.amount());
        newTransaction.setPayer(payer);
        newTransaction.setPayee(payee);
        newTransaction.setTransactionTime(LocalDateTime.now());

        payer.setBalance(payer.getBalance().subtract(transaction.amount()));
        payee.setBalance(payee.getBalance().add(transaction.amount()));

        transactionRepository.save(newTransaction);
        userService.saveUser(payer);
        userService.saveUser(payee);

        notificationService.sendNotification(payer, "Transação realizada com sucesso!");
        notificationService.sendNotification(payee, "Transação recebida com sucesso!");

        return newTransaction;
    }

    public boolean authorizeTransaction() {
        var response = restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", Map.class);

        if(response.getStatusCode() == HttpStatus.OK){
            String message = (String) response.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);
        } else return false;
    }
}
