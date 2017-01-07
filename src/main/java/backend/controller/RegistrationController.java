package backend.controller;

import backend.exceptions.UserExistsException;
import backend.model.User;
import backend.repository.UserRepository;
import backend.service.EmailService;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.util.UUID;

@RestController
public class RegistrationController {

    private final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailService;

    @CrossOrigin
    @RequestMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) throws UserExistsException {
        if(!userRepository.findByLogin(user.getLogin()).isEmpty()){
            logger.warn("User \"" + user.getLogin() + "\" already exists!");
            throw new UserExistsException();
        }
        user.setType("normal");
        user.setToken(UUID.randomUUID().toString());
        user.setEnabled(true);
        String password = RandomStringUtils.randomAlphanumeric(10);
        user.setPassword(DigestUtils.sha512Hex(password));
        userRepository.save(user);
        emailService.sendEmail(user, password);
        logger.info("User added to database:" + user.toString());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}