package com.review.reviewservice.repositories;

import com.review.reviewservice.models.LocalBusiness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalBusinessRepository extends JpaRepository<LocalBusiness,Long> {

}
