package backend.controller;

import backend.model.User;
import backend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class RegistrationController {

    private final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        if(!userRepository.findByLogin(user.getLogin()).isEmpty()){
            logger.warn("User \"" + user.getLogin() + "\" already exists!");
            return new ResponseEntity("Użytkownik o podanym loginie już istnieje!", HttpStatus.NOT_ACCEPTABLE);
        }
        user.setType("normal");
        user.setToken(UUID.randomUUID().toString());
        userRepository.save(user);
        logger.info("User added to database:" + user.toString());
        return new ResponseEntity("Zarejestrowano!", HttpStatus.OK);
    }
}