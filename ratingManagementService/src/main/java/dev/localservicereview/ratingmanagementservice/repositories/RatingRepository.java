package dev.localservicereview.ratingmanagementservice.repositories;

import dev.localservicereview.ratingmanagementservice.modals.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository
        extends JpaRepository<Rating, Long> {
    List<Rating> findByServiceId(Long serviceId);
    void deleteById(Long id);
    Optional<Rating> findById(Long ratingId);

    List<Rating> findAllByServiceId(Long serviceId);


}

