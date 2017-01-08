package backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import backend.model.Offer;
import backend.model.User;

@RepositoryRestResource(exported = false)
public interface OfferRepository extends
		PagingAndSortingRepository<Offer, Integer> {

	@Query("select c from Offer c where c.user.login like %?1"
			+ " and c.title like %?2 " + " and c.type like %?3"
			+ " and c.place like %?4" + " and c.purpose like %?5"
			+ " and c.price <= ?6")
	Page<Offer> findByUserAndFilter(@Param("login") String login,
			@Param("title") String title, @Param("type") String type,
			@Param("place") String place, @Param("purpose") String purpose,
			@Param("price") String price, Pageable pageable);

	@Query("select c from Offer c where c.title like %?1"
			 + " and c.type like %?2"
			+ " and c.place like %?3" + " and c.purpose like %?4"
			+ " and c.price <= ?5")
	Page<Offer> findByFilter(@Param("title") String title,
			@Param("type") String type, @Param("place") String place,
			@Param("purpose") String purpose, @Param("price") String price,
			Pageable pageable);

	Page<Offer> findByUserAndArchived(@Param("user") User user,
			@Param("archived") Boolean archived, Pageable pageable);

}
