package pacifico.davidson.picpaysimplificado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pacifico.davidson.picpaysimplificado.domain.user.User;

public interface UserRepository extends JpaRepository<User,Long> {
}
