package RatingManagementService.ratingmanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "rating")
    private Double rating;
    @Column(name = "service_id")
    private Long serviceId;
}
