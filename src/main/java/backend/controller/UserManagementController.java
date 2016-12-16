package backend.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import backend.exceptions.UnauthorizedException;
import backend.exceptions.UserNotFoundException;
import backend.model.Contact;
import backend.model.User;
import backend.repository.UserRepository;

@RestController
public class UserManagementController {

    private final Logger logger = LoggerFactory.getLogger(UserManagementController.class);

    @Autowired
    UserRepository userRepository;

    @CrossOrigin
    @RequestMapping("/user/get")
    public ResponseEntity<Contact> getUserByToken(@RequestHeader("Authorization") String token) throws UserNotFoundException {
        List<User> users= userRepository.findByToken(token);
        if(users.size() != 1){
            logger.warn("Something went wrong, user list: " + users);
            throw new UserNotFoundException();
        }
        logger.info("Returning contact: " + users.get(0).toContact());
        return new ResponseEntity<>(users.get(0).toContact(), HttpStatus.OK);
    }
    
    @CrossOrigin
    @RequestMapping(method = {RequestMethod.PUT}, value = {"/user/update"})
    public ResponseEntity<String> updateUser(@RequestHeader("Authorization") String token, @RequestBody Contact contact) throws UnauthorizedException, UserNotFoundException{
    	List<User> users = userRepository.findByToken(token);
    	if(users.size() != 1){
            logger.warn("Something went wrong, user list: " + users);
            throw new UserNotFoundException();
        }
    	users.get(0).setFirstName(contact.getFirstName());
    	users.get(0).setLastName(contact.getLastName());
    	users.get(0).setTelephone(contact.getTelephone());
    	users.get(0).setEmail(contact.getEmail());
    	userRepository.save(users.get(0));
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @CrossOrigin
    @RequestMapping(method = {RequestMethod.PUT}, value = {"/user/password/update"})
    public ResponseEntity<String> changePassword(@RequestHeader("Authorization") String token, @RequestBody String newPassword) throws UnauthorizedException, UserNotFoundException{
    	List<User> users = userRepository.findByToken(token);
    	if(users.size() != 1){
            logger.warn("Something went wrong, user list: " + users);
            throw new UnauthorizedException();
        }
    	users.get(0).setPassword(newPassword);
    	userRepository.save(users.get(0));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
