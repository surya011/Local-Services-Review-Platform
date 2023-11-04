package RatingManagementService.ratingmanagement.Controllers;

import RatingManagementService.ratingmanagement.Services.RatingServices;
import RatingManagementService.ratingmanagement.model.Rating;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final RatingServices ratingServices;

    public RatingController(RatingServices ratingServices) {
        this.ratingServices = ratingServices;
    }

    @PostMapping("/add")
    public void addRating(@RequestBody Rating rating) {
        ratingServices.addRating(rating);
    }

    @PutMapping()
    public void updateRating(@RequestParam long userId,@RequestParam long serviceId, @RequestBody Rating rating) {

        ratingServices.updateRatingByUserIdAndServiceId(userId,serviceId, rating);
    }

    @DeleteMapping()
    public void deleteRating(@RequestParam long userId,@RequestParam long serviceId) {
        ratingServices.deleteRatingByUserIdAndServiceId(userId, serviceId);
    }
}
