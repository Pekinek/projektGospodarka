package backend.controller;

import backend.exceptions.UserNotFoundException;
import backend.model.Contact;
import backend.model.User;
import backend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserManagementController {

    private final Logger logger = LoggerFactory.getLogger(UserManagementController.class);

    @Autowired
    UserRepository userRepository;

    @CrossOrigin
    @RequestMapping("/user/get")
    public ResponseEntity<Contact> getUserByToken(@RequestBody String token) throws UserNotFoundException {
        List<User> users= userRepository.findByToken(token);
        if(users.size() != 1){
            logger.warn("Something went wrong, user list: " + users);
            throw new UserNotFoundException();
        }
        logger.info("Returning contact: " + users.get(0).toContact());
        return new ResponseEntity<>(users.get(0).toContact(), HttpStatus.OK);
    }
}
