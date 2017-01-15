package backend.controller;

import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.exceptions.UnauthorizedException;
import backend.model.Issue;
import backend.model.Offer;
import backend.model.User;
import backend.repository.IssueRepository;
import backend.repository.UserRepository;

@RestController
@CrossOrigin
public class IssueController {

	@Autowired
	IssueRepository issueRepository;

	@Autowired
	UserRepository userRepository;

	@CrossOrigin
	@RequestMapping("/issue/add")
	public ResponseEntity<String> addIssue(@RequestBody Issue issue) {
		issue.setDate(new Date().getTime());
		issueRepository.save(issue);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping("/admin/issue/all")
	public ResponseEntity<PagedResources<Resource<Issue>>> getIssues(
			@RequestHeader("Authorization") String token, Pageable pageable,
			PagedResourcesAssembler<Issue> assembler)
			throws UnauthorizedException {

		List<User> users = userRepository.findByToken(token);
		if (users.size() != 1) {
			throw new UnauthorizedException();
		}

		Page<Issue> issues = issueRepository.findAll(pageable);

		return new ResponseEntity<PagedResources<Resource<Issue>>>(
				assembler.toResource(issues), HttpStatus.OK);
	}
}
