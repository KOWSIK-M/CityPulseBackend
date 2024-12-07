package com.klef.jfsd.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.springboot.model.FeedbackDTO;
import com.klef.jfsd.springboot.model.Feedbacks;
import com.klef.jfsd.springboot.model.Users;
import com.klef.jfsd.springboot.repo.FeedbackRepository;
import com.klef.jfsd.springboot.repo.UserRepository;

@Service
public class FeedbackServiceImpl implements FeedbackService{

	@Autowired
	private FeedbackRepository feedbackRepository;

    @Autowired
    private UserRepository userRepository;
    
    public void submitFeedback(FeedbackDTO feedbackData) {
        // Validate the user
        Users user = userRepository.findByEmail(feedbackData.getFemail());

        // Create and save the feedback
        Feedbacks feedback = new Feedbacks();
        feedback.setUser(user);
        feedback.setLocation(feedbackData.getLocation());
        feedback.setCity(feedbackData.getCity());
        feedback.setFeedback(feedbackData.getFeedback());
        feedback.setRating(feedbackData.getRating());

        feedbackRepository.save(feedback);
    }
    
    public Long countFeedbacks() {
    	return feedbackRepository.count();
    }
    
    public List<Feedbacks> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    
    public List<Feedbacks> getApprovedFeedbacks() {
        return feedbackRepository.findApprovedFeedbacks();
    }

    // Fetch all unapproved feedbacks
    public List<Feedbacks> getUnapprovedFeedbacks() {
        return feedbackRepository.findUnapprovedFeedbacks();
    }

    // Fetch feedbacks based on dynamic approval status
    public List<Feedbacks> getFeedbacksByApprovalStatus(boolean approved) {
        return feedbackRepository.findByApprovalStatus(approved);
    }
    
    public boolean approveFeedback(int feedbackId) {
        Optional<Feedbacks> feedbackOptional = feedbackRepository.findById(feedbackId);
        if (feedbackOptional.isPresent()) {
            Feedbacks feedback = feedbackOptional.get();
            feedback.setApproved(true);
            feedbackRepository.save(feedback);
            return true;
        }
        return false;
    }

}
