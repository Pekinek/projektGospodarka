package backend.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.exceptions.InvalidPasswordException;
import backend.exceptions.UserDisabledException;
import backend.exceptions.UserNotFoundException;
import backend.model.Token;
import backend.model.User;
import backend.repository.UserRepository;

@RestController
public class LoginController {

	private final Logger logger = LoggerFactory
			.getLogger(LoginController.class);

	@Autowired
	UserRepository userRepository;

	@CrossOrigin
	@RequestMapping("/login")
	public ResponseEntity<Token> login(@RequestBody User loginData)
			throws UserNotFoundException, InvalidPasswordException, UserDisabledException {
		List<User> users = userRepository.findByLogin(loginData.getLogin());
		if (users.size() != 1) {
			logger.warn("User not found: " + loginData.getLogin());
			throw new UserNotFoundException();
		}
		User user = users.get(0);
		if (!user.getPassword().equals(loginData.getPassword())) {
			logger.warn("Expected password: " + user.getPassword()
					+ ", received: " + loginData.getPassword() + " for user: "
					+ loginData.getLogin());
			throw new InvalidPasswordException();
		}
		if (user.getEnabled() == false) {
			throw new UserDisabledException();
		}
		logger.info("User logged: " + user.getLogin());
		return new ResponseEntity<>(new Token(user.getToken(), user.getLogin(),
				user.getType()), HttpStatus.OK);
	}
}
