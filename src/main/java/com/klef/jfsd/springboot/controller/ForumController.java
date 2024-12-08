package com.klef.jfsd.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

import com.klef.jfsd.springboot.model.Forum;
import com.klef.jfsd.springboot.model.ForumDTO;
import com.klef.jfsd.springboot.model.Comment;
import com.klef.jfsd.springboot.model.FeedbackDTO;
import com.klef.jfsd.springboot.service.ForumService;

@RestController
@RequestMapping("/api/forums")
@CrossOrigin(origins = {"https://citypulse-kowsik.vercel.app", "http://localhost:3000"})
public class ForumController {

    @Autowired
    private ForumService forumService;

    
    @PostMapping("/add")
    public ResponseEntity<?> submitForum(@RequestBody ForumDTO forumData) {
        try {
            forumService.addForum(forumData);
            return ResponseEntity.ok("Forum submitted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    @PutMapping(value = "/like/{forumId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Forum> likeForum(
        @PathVariable int forumId,
        @RequestBody Map<String, Boolean> payload
    ) {
        boolean like = payload.getOrDefault("like", false);
        Forum updatedForum = forumService.updateLikes(forumId, like);
        return ResponseEntity.ok(updatedForum);
    }




    // Add a Comment
    @PostMapping("/comment")
    public Comment addComment(@RequestBody Comment comment) {
        return forumService.addComment(comment);
    }

    // Get All Forums
    @GetMapping("/all")
    public List<Forum> getAllForums() {
        return forumService.getAllForums();
    }
}
