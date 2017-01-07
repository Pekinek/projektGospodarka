package backend.controller;

import backend.exceptions.OfferNotFoundException;
import backend.exceptions.UnauthorizedException;
import backend.model.Offer;
import backend.model.User;
import backend.repository.OfferRepository;
import backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FavouriteController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OfferRepository offerRepository;

    @CrossOrigin
    @RequestMapping("/offers/{id}/addFavourite")
    public ResponseEntity<String> addToFavourites(@PathVariable("id") Integer offerId, @RequestHeader("Authorization") String token) throws UnauthorizedException, OfferNotFoundException {
        List<User> users = userRepository.findByToken(token);
        if (users.size() != 1) {
            throw new UnauthorizedException();
        }
        Offer offer = offerRepository.findOne(offerId);
        if(offer == null) {
            throw new OfferNotFoundException();
        }
        User user = users.get(0);
        user.getFavouriteOffers().add(offer);
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping("/offers/favourites")
    public ResponseEntity<Iterable<Offer>> getFavourites(@RequestHeader("Authorization") String token) throws UnauthorizedException {
        List<User> users = userRepository.findByToken(token);
        if (users.size() != 1) {
            throw new UnauthorizedException();
        }
        return new ResponseEntity<>(users.get(0).getFavouriteOffers(), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping("/offers/{id}/removeFavourite")
    public ResponseEntity<String> removeFromFavourites(@PathVariable("id") Integer offerId, @RequestHeader("Authorization") String token) throws UnauthorizedException, OfferNotFoundException {
        List<User> users = userRepository.findByToken(token);
        if (users.size() != 1) {
            throw new UnauthorizedException();
        }
        Offer offer = offerRepository.findOne(offerId);
        if(offer == null) {
            throw new OfferNotFoundException();
        }
        User user = users.get(0);
        user.getFavouriteOffers().remove(offer);
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
