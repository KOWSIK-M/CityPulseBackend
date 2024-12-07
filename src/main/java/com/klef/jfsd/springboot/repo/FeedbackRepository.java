package com.klef.jfsd.springboot.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.klef.jfsd.springboot.model.Feedbacks;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedbacks, Integer>{
    // Query to get only approved feedbacks
    @Query("SELECT f FROM Feedbacks f WHERE f.approved = true")
    List<Feedbacks> findApprovedFeedbacks();

    // Query to get only unapproved feedbacks
    @Query("SELECT f FROM Feedbacks f WHERE f.approved = false")
    List<Feedbacks> findUnapprovedFeedbacks();

    // Query to get feedbacks based on approval status (approved or unapproved)
    @Query("SELECT f FROM Feedbacks f WHERE f.approved = :approved")
    List<Feedbacks> findByApprovalStatus(boolean approved);

}
