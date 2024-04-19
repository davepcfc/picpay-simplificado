package pacifico.davidson.picpaysimplificado.domain.user;

import java.math.BigDecimal;

public record UserDTO(
        String name,
        String document,
        String email,
        String password,
        UserType userType,
        BigDecimal balance
) {
}
