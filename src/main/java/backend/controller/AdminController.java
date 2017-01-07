package backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.exceptions.UnauthorizedException;
import backend.exceptions.UserNotFoundException;
import backend.model.Comment;
import backend.model.Offer;
import backend.model.User;
import backend.repository.CommentRepository;
import backend.repository.OfferRepository;
import backend.repository.UserRepository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	OfferRepository offerRepository;

	@Autowired
	CommentRepository commentRepository;

	@RequestMapping("/offers/all")
	public ResponseEntity<PagedResources<Resource<Offer>>> getAllOffers(
			@RequestParam(value = "filter", required = false) String filter,
			Pageable pageable, PagedResourcesAssembler<Offer> assembler) {
		Page<Offer> offers = offerRepository.findAll(pageable);
		for (Offer offer : offers) {
			offer.setPictures(null);
		}
		return new ResponseEntity<PagedResources<Resource<Offer>>>(
				assembler.toResource(offers), HttpStatus.OK);
	}

	@RequestMapping(method = { RequestMethod.PUT }, value = { "/offer/update" })
	public ResponseEntity<String> updateUser(
			@RequestHeader("Authorization") String token,
			@RequestBody Integer id) throws UnauthorizedException,
			UserNotFoundException {
		List<User> users = userRepository.findByToken(token);
		if (users.size() != 1) {
			throw new UserNotFoundException();
		}
		System.out.println(users.get(0).getType());
		if (!users.get(0).getType().equals("admin")) {
			throw new UnauthorizedException();
		}
		Offer offer = offerRepository.findOne(id);
		offer.setArchived(!offer.getArchived());
		offerRepository.save(offer);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping("/users/all")
	public ResponseEntity<PagedResources<Resource<User>>> getUsers(
			@RequestParam(value = "filter", required = false) String filter,
			Pageable pageable, PagedResourcesAssembler<User> assembler) {
		Page<User> users = userRepository.findByType("normal", pageable);

		return new ResponseEntity<PagedResources<Resource<User>>>(
				assembler.toResource(users), HttpStatus.OK);
	}

	@RequestMapping(method = { RequestMethod.PUT }, value = { "/user/enable" })
	public ResponseEntity<String> enableUser(
			@RequestHeader("Authorization") String token,
			@RequestBody String login) throws UnauthorizedException,
			UserNotFoundException {
		List<User> users = userRepository.findByToken(token);
		if (users.size() != 1) {
			throw new UnauthorizedException();
		}
		if (!users.get(0).getType().equals("admin")) {
			throw new UnauthorizedException();
		}
		User user = userRepository.findOne(login);
		user.setEnabled(true);
		userRepository.save(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(method = { RequestMethod.PUT }, value = { "/user/disable" })
	public ResponseEntity<String> disableUser(
			@RequestHeader("Authorization") String token,
			@RequestBody String login) throws UnauthorizedException,
			UserNotFoundException {
		List<User> users = userRepository.findByToken(token);
		if (users.size() != 1) {
			throw new UnauthorizedException();
		}
		if (!users.get(0).getType().equals("admin")) {
			throw new UnauthorizedException();
		}
		User user = userRepository.findOne(login);
		user.setEnabled(false);
		userRepository.save(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(method = { RequestMethod.DELETE }, value = { "/offers/delete/{id}" })
	public ResponseEntity<Iterable<Offer>> removeOffer(
			@RequestHeader("Authorization") String token,
			@PathVariable Integer id) throws UnauthorizedException {

		List<User> users = userRepository.findByToken(token);

		if (users.get(0).getType().equals("admin")) {
			Offer offer = offerRepository.findOne(id);
			List<Comment> comments = commentRepository.findByOfferId(id);
			users = (List<User>) userRepository.findAll();

			for (User u : users) {
				u.getFavouriteOffers().remove(offer);
			}

			commentRepository.delete(comments);
			offerRepository.delete(offer);
		} else {
			throw new UnauthorizedException();
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
