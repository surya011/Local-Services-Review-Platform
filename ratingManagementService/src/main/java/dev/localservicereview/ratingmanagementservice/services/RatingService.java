package dev.localservicereview.ratingmanagementservice.services;

import dev.localservicereview.ratingmanagementservice.dtos.RatingDto;
import dev.localservicereview.ratingmanagementservice.dtos.ServiceRatingResponseDto;
import dev.localservicereview.ratingmanagementservice.exceptions.NotFoundException;

import java.util.List;

public interface RatingService {
    ServiceRatingResponseDto createRating(RatingDto request);

    ServiceRatingResponseDto getServiceRating(Long serviceId);

    ServiceRatingResponseDto updateRatingById(Long ratingId, RatingDto request) throws NotFoundException;

    void deleteRating(Long ratingId);

    List<RatingDto> getAllRatingsForService(Long serviceId);
}
