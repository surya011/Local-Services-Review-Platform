package dev.localservicereview.ratingmanagementservice.controllers;

import dev.localservicereview.ratingmanagementservice.dtos.RatingDto;
import dev.localservicereview.ratingmanagementservice.dtos.ServiceRatingResponseDto;
import dev.localservicereview.ratingmanagementservice.exceptions.NotFoundException;
import dev.localservicereview.ratingmanagementservice.services.RatingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController<RatingRequest> {
    private RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping
    public ServiceRatingResponseDto createRating(@RequestBody RatingDto request) {
        return ratingService.createRating(request);
    }

    @GetMapping("/service/{serviceId}")
    public ServiceRatingResponseDto getServiceRating(@PathVariable Long serviceId) {
      return ratingService.getServiceRating(serviceId);
    }

    @PutMapping("/{ratingId}")
    public ServiceRatingResponseDto updateRatingById(@PathVariable("ratingId") Long ratingId, @RequestBody RatingDto request) throws NotFoundException {
        return ratingService.updateRatingById(ratingId, request);
    }

    @DeleteMapping("/{ratingId}")
    public void deleteRating(@PathVariable Long ratingId) {
        ratingService.deleteRating(ratingId);
    }

    @GetMapping("/service/{serviceId}/all")
    public List<RatingDto> getAllRatingsForService(@PathVariable Long serviceId) {
        return ratingService.getAllRatingsForService(serviceId);
    }

}
