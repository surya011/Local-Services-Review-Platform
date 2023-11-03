package dev.localservicereview.ratingmanagementservice.services;

import dev.localservicereview.ratingmanagementservice.dtos.RatingDto;
import dev.localservicereview.ratingmanagementservice.dtos.ServiceRatingResponseDto;
import dev.localservicereview.ratingmanagementservice.exceptions.NotFoundException;
import dev.localservicereview.ratingmanagementservice.modals.Rating;
import dev.localservicereview.ratingmanagementservice.repositories.RatingRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Primary
@Service
public class RatingServiceImplementation implements RatingService{

    private RatingRepository ratingRepository;

    public RatingServiceImplementation(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public ServiceRatingResponseDto createRating(RatingDto request) {

        Rating rating = new Rating();
        rating.setComment(request.getComment());
        rating.setServiceId(request.getServiceId());
        rating.setRating(request.getRating());
        ratingRepository.save(rating);

        double averageRating = calculateAverageRating(rating.getServiceId());
        int totalRatings = calculateTotalRatings(rating.getServiceId());

        ServiceRatingResponseDto response = new ServiceRatingResponseDto();
        response.setAverageRating(averageRating);
        response.setTotalRatings(totalRatings);

        return response;

    }

    @Override
    public ServiceRatingResponseDto getServiceRating(Long serviceId) {

        double averageRating = calculateAverageRating(serviceId);
        int totalRatings = calculateTotalRatings(serviceId);

        ServiceRatingResponseDto response = new ServiceRatingResponseDto();
        response.setAverageRating(averageRating);
        response.setTotalRatings(totalRatings);

        return response;
    }

    @Override
    public ServiceRatingResponseDto updateRatingById(Long ratingId, RatingDto request) throws NotFoundException {

        Optional<Rating> existingRating = ratingRepository.findById(ratingId);

        if (existingRating.isEmpty()) {
            throw new NotFoundException("Rating with ID " + ratingId + " not found");
        }

        Rating updateProduct = existingRating.get();

        updateProduct.setServiceId(request.getServiceId());
        updateProduct.setComment(request.getComment());
        updateProduct.setRating(request.getRating());

        ratingRepository.save(updateProduct);

        ServiceRatingResponseDto serviceRatingResponseDto = new ServiceRatingResponseDto();
        serviceRatingResponseDto.setAverageRating(calculateAverageRating(updateProduct.getServiceId()));
        serviceRatingResponseDto.setTotalRatings(calculateTotalRatings(updateProduct.getServiceId()));
        return serviceRatingResponseDto;

    }

    @Override
    public void deleteRating(Long ratingId) {
        ratingRepository.deleteById(ratingId);
    }

    @Override
    public List<RatingDto> getAllRatingsForService(Long serviceId) {
        List<Rating> ratings = ratingRepository.findAllByServiceId(serviceId);

        List<RatingDto> ratingDtos = new ArrayList<>();

        for(Rating rating : ratings){
            RatingDto ratingDto = new RatingDto();
            ratingDto.setServiceId(rating.getServiceId());
            ratingDto.setComment(rating.getComment());
            ratingDto.setRating(rating.getRating());
            ratingDtos.add(ratingDto);
        }

        return ratingDtos;
    }

    public double calculateAverageRating(Long serviceId) {
        List<Rating> ratings = ratingRepository.findByServiceId(serviceId);

        if (ratings.isEmpty()) {
            return 0.0;
        }

        double totalRating = 0.0;
        for (Rating rating : ratings) {
            totalRating += rating.getRating();
        }

        double averageRating = totalRating / ratings.size();;
        String formattedAverageRating = String.format("%.2f", averageRating);
        return Double.parseDouble(formattedAverageRating);
    }

    public int calculateTotalRatings(Long serviceId) {
        List<Rating> ratings = ratingRepository.findByServiceId(serviceId);
        return ratings.size();
    }
}
