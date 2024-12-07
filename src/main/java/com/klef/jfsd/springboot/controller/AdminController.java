package com.klef.jfsd.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klef.jfsd.springboot.model.Admin;
import com.klef.jfsd.springboot.model.Feedbacks;
import com.klef.jfsd.springboot.model.Users;
import com.klef.jfsd.springboot.repo.UserRepository;
import com.klef.jfsd.springboot.service.AdminService;
import com.klef.jfsd.springboot.service.FeedbackService;
import com.klef.jfsd.springboot.service.UserService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FeedbackService feedbackService;
	
	@PostMapping("/login")
    public Map<String, Object> login(@RequestBody Admin admin) {
        return adminService.verifyAdmin(admin);
    }
	
    @GetMapping("/count")
    public ResponseEntity<Long> getUserCount() {
        long count = userService.countUsers();
        return ResponseEntity.ok(count);
    }
    
    @GetMapping("/feedback-count")
    public ResponseEntity<Long> getFeedbackCount() {
        long count = feedbackService.countFeedbacks();
        return ResponseEntity.ok(count);
    }
    
	
    @GetMapping("/payment-stats")
    public Map<String, Integer> getUserPaymentStats() {
        int paidCount = userRepository.countByPaid(true);
        int unpaidCount = userRepository.countByPaid(false);

        Map<String, Integer> stats = new HashMap<>();
        stats.put("Paid", paidCount);
        stats.put("Unpaid", unpaidCount);

        return stats;
    }
    
    @GetMapping("/city-stats")
    public Map<String, Integer> getUserCityStats() {
        // Fetch the count of users grouped by city
        Map<String, Integer> cityStats = userRepository.findCityWiseUserCount();

        // Return the result
        return cityStats;
    }
    
    @GetMapping("/allusers")
    public List<Users> usersList(){
    	return userService.getAllUsers();
    }
    
    @GetMapping("/allfeedbacks")
    public List<Feedbacks> getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }
    
//    @PutMapping("/approve/{feedbackId}")
//    public void approveFeedback(@PathVariable int feedbackId) {
//        feedbackService.approveFeedback(feedbackId);
//    }
//
//    // Endpoint to get all unapproved feedbacks
//    @GetMapping("/unapproved")
//    public List<Feedbacks> getUnapprovedFeedbacks() {
//        return feedbackService.getUnapprovedFeedbacks();
//    }
    
    @GetMapping("/unapproved")
    public List<Feedbacks> getUnapprovedFeedbacks() {
        return feedbackService.getUnapprovedFeedbacks();
    }

    // Get approved feedbacks
    @GetMapping("/approved")
    public List<Feedbacks> getApprovedFeedbacks() {
        return feedbackService.getApprovedFeedbacks();
    }

    // Get feedbacks by approval status (either true or false)
    @GetMapping("/status/{approved}")
    public List<Feedbacks> getFeedbacksByApprovalStatus(@PathVariable boolean approved) {
        return feedbackService.getFeedbacksByApprovalStatus(approved);
    }
    
    @PutMapping("/approve/{feedbackId}")
    public ResponseEntity<String> approveFeedback(@PathVariable int feedbackId) {
        boolean result = feedbackService.approveFeedback(feedbackId);
        if (result) {
            return ResponseEntity.ok("Feedback approved successfully.");
        } else {
            return ResponseEntity.badRequest().body("Feedback approval failed.");
        }
    }


}
