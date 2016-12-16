package backend.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import backend.exceptions.UnauthorizedException;
import backend.model.Comment;
import backend.model.Offer;
import backend.model.User;
import backend.repository.CommentRepository;
import backend.repository.OfferRepository;
import backend.repository.UserRepository;

@RestController
public class OfferController {

    private final Logger logger = LoggerFactory.getLogger(OfferController.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    OfferRepository offerRepository;
    
    @Autowired
    CommentRepository commentRepository;

    @CrossOrigin
    @RequestMapping("/offers/upload")
    public ResponseEntity<String> addOffer(@RequestHeader("Authorization") String token, @RequestBody Offer offer) throws UnauthorizedException {
        List<User> userList = userRepository.findByToken(token);
        if(userList.size()!=1){
            logger.warn("Something went wrong, user list: " + userList);
            throw new UnauthorizedException();
        }
        offer.setUser(userList.get(0));
        offer.setDate(new Date().getTime());
        offer.setArchived(false);
        offerRepository.save(offer);
        logger.info("Offer added: " + offer.toString());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping("/offers/all")
    public ResponseEntity<Iterable<Offer>> getAllOffers(){
        return new ResponseEntity<>(offerRepository.findByArchived(false), HttpStatus.OK);
    }
    
    @CrossOrigin
    @RequestMapping("/offers/{id}")
    public ResponseEntity<Offer> getOfferById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(offerRepository.findOne(id), HttpStatus.OK);
    }
    
    @CrossOrigin
    @RequestMapping("/offers/user/{login}")
    public ResponseEntity<Iterable<Offer>> getUserOffers(@PathVariable("login") String login){
    	List<User> userList = userRepository.findByLogin(login);
        return new ResponseEntity<>(offerRepository.findByUserAndArchived(userList.get(0), false), HttpStatus.OK);
    }
    
    @CrossOrigin
    @RequestMapping(method = {RequestMethod.DELETE}, value = {"/offers/delete/{id}"})
    public ResponseEntity<Iterable<Offer>> removeOffer(@RequestHeader("Authorization") String token, @PathVariable Integer id) throws UnauthorizedException{
    	Offer offer = offerRepository.findOne(id);
    	
    	if(offer.getUser().getToken().equals(token)) {
    		List<Comment> comments = commentRepository.findByOfferId(id);
    		List<User> users = (List<User>) userRepository.findAll();
    		
    		for(User user: users) {
    			user.getFavouriteOffers().remove(offer);
    		}
    		
    		commentRepository.delete(comments);
    		offerRepository.delete(offer);
    	}
    	else {
    		throw new UnauthorizedException();
    	}
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
