package backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import backend.model.Offer;
import backend.model.User;

@RepositoryRestResource(exported = false)
public interface OfferRepository extends PagingAndSortingRepository<Offer, Integer> {
	Page<Offer> findByUser(@Param("user") User user, Pageable pageable);

    Page<Offer> findByArchived(@Param("archived") Boolean archived, Pageable pageable);
    
    Page<Offer> findByUserAndArchived(@Param("user") User user, @Param("archived") Boolean archived,  Pageable pageable);

}
