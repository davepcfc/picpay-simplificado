package pacifico.davidson.picpaysimplificado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pacifico.davidson.picpaysimplificado.domain.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
