package pacifico.davidson.picpaysimplificado.service;

import org.springframework.stereotype.Service;
import pacifico.davidson.picpaysimplificado.domain.user.User;

@Service
public class NotificationService {

    public void sendNotification(User user, String message) {
        System.out.println(user.getEmail() + message);
    }
}
