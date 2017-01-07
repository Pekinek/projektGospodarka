package backend.controller;

import backend.model.Comment;
import backend.model.CommentSummary;
import backend.repository.CommentRepository;
import backend.repository.OfferRepository;
import backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class CommentController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    OfferRepository offerRepository;

    @Autowired
    CommentRepository commentRepository;

    @CrossOrigin
    @RequestMapping("/offers/{id}/addComment")
    public ResponseEntity<String> addComment(@PathVariable("id") Integer offerId, @RequestHeader("Authorization") String token, @RequestBody Comment comment){
        comment.setDate(new Date().getTime());
        comment.setOffer(offerRepository.findOne(offerId));
        comment.setUser(userRepository.findByToken(token).get(0));
        commentRepository.save(comment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping("/offers/{id}/getComments")
    public ResponseEntity<Iterable<CommentSummary>> getCommentsByOffer(@PathVariable("id") Integer offerId){
        Set<Comment> comments = offerRepository.findOne(offerId).getComments();
        Set<CommentSummary> commentSummaries = comments.stream().map(c -> c.toCommentSummary()).collect(Collectors.toSet());
        return new ResponseEntity(commentSummaries, HttpStatus.OK);
    }
}
