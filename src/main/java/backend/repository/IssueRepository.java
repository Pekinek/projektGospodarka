package backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import backend.model.Issue;

@RepositoryRestResource(exported = false)
public interface IssueRepository extends
		PagingAndSortingRepository<Issue, Integer> {
	
	
	Page<Issue> findAll(Pageable pageable);

}
