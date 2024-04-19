package pacifico.davidson.picpaysimplificado.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pacifico.davidson.picpaysimplificado.domain.user.User;
import pacifico.davidson.picpaysimplificado.domain.user.UserDTO;
import pacifico.davidson.picpaysimplificado.domain.user.UserType;
import pacifico.davidson.picpaysimplificado.repositories.UserRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    public User createUser(UserDTO userDTO) {
        User newUser = new User(userDTO);
        this.saveUser(newUser);
                return newUser;
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public User findUserById(Long id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new Exception("Usuário não encontrado."));
    }

    public boolean validateUser(User payer, BigDecimal amount) throws Exception {
        if(payer.getUserType() == UserType.MERCHANT) {
            throw new Exception("Um lojista não pode realizar transações.");
        }

        if(payer.getBalance().compareTo(amount) < 0) {
            throw new Exception("Saldo insuficiente.");
        }

        return true;
    }
}
