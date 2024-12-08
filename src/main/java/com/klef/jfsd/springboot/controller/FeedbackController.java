package com.klef.jfsd.springboot.controller;

import com.klef.jfsd.springboot.model.FeedbackDTO;
import com.klef.jfsd.springboot.model.Feedbacks;
import com.klef.jfsd.springboot.model.Users;
import com.klef.jfsd.springboot.repo.FeedbackRepository;
import com.klef.jfsd.springboot.repo.UserRepository;
import com.klef.jfsd.springboot.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"https://citypulse-kowsik.vercel.app", "http://localhost:3000"})
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;
    
    @PostMapping("/feedbacks")
    public ResponseEntity<?> submitFeedback(@RequestBody FeedbackDTO feedbackData) {
        try {
            feedbackService.submitFeedback(feedbackData);
            return ResponseEntity.ok("Feedback submitted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
