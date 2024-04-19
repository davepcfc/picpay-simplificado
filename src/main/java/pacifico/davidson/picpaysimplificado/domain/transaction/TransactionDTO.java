package pacifico.davidson.picpaysimplificado.domain.transaction;

import java.math.BigDecimal;

public record TransactionDTO(
        BigDecimal amount,
        Long payerId,
        Long payeeId
) {
}
