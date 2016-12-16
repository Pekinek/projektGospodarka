package backend.repository;

import backend.model.Offer;
import backend.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface OfferRepository extends PagingAndSortingRepository<Offer, Integer> {
    List<Offer> findByUser(@Param("user") User user);

    List<Offer> findByArchived(@Param("archived") Boolean archived);
    
    List<Offer> findByUserAndArchived(@Param("user") User user, @Param("archived") Boolean archived);

}
