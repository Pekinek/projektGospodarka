package backend.repository;

import java.util.List;

import backend.model.Comment;
import backend.model.Offer;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface CommentRepository extends
		PagingAndSortingRepository<Comment, Integer> {

	List<Comment> findByOfferId(@Param("id") Integer id);

}
