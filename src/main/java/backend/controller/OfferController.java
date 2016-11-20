package backend.controller;

import backend.model.Offer;
import backend.model.User;
import backend.repository.OfferRepository;
import backend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping("/offers/upload")
    public ResponseEntity<String> addOffer(@RequestBody Offer offer) {
        List<User> userList = userRepository.findByToken(offer.getUserToken());
        if(userList.size()==1){
            offer.setUser(userList.get(0));
            offerRepository.save(offer);
            logger.info("Offer added: " + offer.toString());
            return new ResponseEntity<>("Zapisano ofertę!", HttpStatus.OK);
        }
        logger.warn("Something went wrong, user list: " + userList);
        return new ResponseEntity<>("Invalid token", HttpStatus.NOT_ACCEPTABLE);
    }

    @RequestMapping("/offers/all")
    public ResponseEntity<Iterable<Offer>> getAllOffers(){
        return new ResponseEntity<>(offerRepository.findAll(), HttpStatus.OK);
    }

}
