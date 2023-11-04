package RatingManagementService.ratingmanagement.Services;

import RatingManagementService.ratingmanagement.model.Rating;
import org.springframework.web.bind.annotation.RequestBody;

public interface RatingServices {
   void addRating(Rating rating);


    void updateRatingByUserIdAndServiceId(long userId, long serviceId, Rating rating);

    void deleteRatingByUserIdAndServiceId(long userId, long serviceId);
}
