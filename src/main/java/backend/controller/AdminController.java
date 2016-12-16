package backend.controller;

import java.util.ArrayList;
import java.util.List;

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
import backend.model.Offer;
import backend.model.User;
import backend.repository.OfferRepository;
import backend.repository.UserRepository;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	OfferRepository offerRepository;
	
    @RequestMapping("/offers/all")
    public ResponseEntity<Iterable<Offer>> getAllOffers(){
		List<Offer> offers = (List<Offer>) offerRepository.findAll();
		for(Offer offer : offers) {
			offer.setPictures(null);
		}
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }
	
	@RequestMapping(method = {RequestMethod.PUT}, value = {"/offer/update"})
    public ResponseEntity<String> updateUser(@RequestHeader("Authorization") String token, @RequestBody Integer id) throws UnauthorizedException, UserNotFoundException{
    	List<User> users = userRepository.findByToken(token);
    	if(users.size() != 1){
            throw new UserNotFoundException();
        }
    	if(users.get(0).getType() != "admin"){
            throw new UnauthorizedException();
        }
    	Offer offer = offerRepository.findOne(id);
    	offer.setArchived(!offer.getArchived());
    	offerRepository.save(offer);
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
    @RequestMapping("/users/all")
    public ResponseEntity<Iterable<Contact>> getUsers(){
		List<User> users = (List<User>) userRepository.findByType("normal");
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		
		for(User user: users) {
			contacts.add(user.toContact());
		}
		
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }
    
    @RequestMapping(method = {RequestMethod.PUT}, value = {"/user/enable"})
    public ResponseEntity<String> enableUser(@RequestHeader("Authorization") String token, @RequestBody String login) throws UnauthorizedException, UserNotFoundException{
    	List<User> users = userRepository.findByToken(token);
    	if(users.size() != 1 || users.get(0).getType() != "admin"){
            throw new UnauthorizedException();
        }
    	User user = userRepository.findOne(login);
    	user.setEnabled(true);
    	userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @RequestMapping(method = {RequestMethod.PUT}, value = {"/user/disable"})
    public ResponseEntity<String> disableUser(@RequestHeader("Authorization") String token, @RequestBody String login) throws UnauthorizedException, UserNotFoundException{
    	List<User> users = userRepository.findByToken(token);
    	if(users.size() != 1 || users.get(0).getType() != "admin"){
            throw new UnauthorizedException();
        }
    	User user = userRepository.findOne(login);
    	user.setEnabled(false);
    	userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
