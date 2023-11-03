package dev.localservicereview.ratingmanagementservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceRatingResponseDto {
    private double averageRating;
    private int totalRatings;
}
