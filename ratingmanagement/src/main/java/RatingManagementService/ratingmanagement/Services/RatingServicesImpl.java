package RatingManagementService.ratingmanagement.Services;

import RatingManagementService.ratingmanagement.Repositories.RatingRepository;
import RatingManagementService.ratingmanagement.model.Rating;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class RatingServicesImpl implements RatingServices{

    private final RatingRepository ratingRepository;

    public RatingServicesImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }
    @Override
    public void addRating(Rating rating) {

        ratingRepository.save(rating);
        System.out.println("Rating added");
    }

    @Override
    public void updateRatingByUserIdAndServiceId(long userId,long serviceId, Rating rating) {


        Rating existingRating = ratingRepository.findByUSerIdAndServiceId(userId,serviceId);

        if (existingRating != null) {
            // Update the fields with values from the request body
            if (rating.getUserId() != null) {
                existingRating.setUserId(rating.getUserId());
            } else {
                existingRating.setUserId(existingRating.getUserId());
            }
            if (rating.getRating() != null) {
                existingRating.setRating(rating.getRating());
            } else {
                existingRating.setRating(existingRating.getRating());
            }
            if (rating.getServiceId() != null) {
                existingRating.setServiceId(rating.getServiceId());
            } else {
                existingRating.setServiceId(existingRating.getServiceId());
            }

            // Save the updated rating object
                 ratingRepository.save(existingRating);

        }
        else{
            System.out.println("Rating not found");
        }

        System.out.println("Rating updated");
    }

    @Override
    public void deleteRatingByUserIdAndServiceId( long userId, long serviceId) {
       ratingRepository.deleteBYUserIdAndServiceId(userId, serviceId);
        System.out.println("Rating deleted");
    }
}
