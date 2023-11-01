package com.review.reviewservice.models;

import com.review.reviewservice.dtos.ServiceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Service")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalBusiness {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String service_name;
    @Enumerated(EnumType.STRING)
    private ServiceType service_type;
    private String city;


}
