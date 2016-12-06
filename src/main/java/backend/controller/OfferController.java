package backend.controller;

import backend.exceptions.UnauthorizedException;
import backend.exceptions.UserNotFoundException;
import backend.model.Offer;
import backend.model.User;
import backend.repository.OfferRepository;
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
public class OfferController {

    private final Logger logger = LoggerFactory.getLogger(OfferController.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    OfferRepository offerRepository;

    @CrossOrigin
    @RequestMapping("/offers/upload")
    public ResponseEntity<String> addOffer(@RequestBody Offer offer) throws UnauthorizedException {
        List<User> userList = userRepository.findByToken(offer.getUserToken());
        if(userList.size()!=1){
            logger.warn("Something went wrong, user list: " + userList);
            throw new UnauthorizedException();
        }
        offer.setUser(userList.get(0));
        offerRepository.save(offer);
        logger.info("Offer added: " + offer.toString());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping("/offers/all")
    public ResponseEntity<Iterable<Offer>> getAllOffers(){
        return new ResponseEntity<>(offerRepository.findAll(), HttpStatus.OK);
    }

}
