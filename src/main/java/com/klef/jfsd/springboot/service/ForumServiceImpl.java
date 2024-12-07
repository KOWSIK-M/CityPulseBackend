package com.klef.jfsd.springboot.service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.springboot.model.Comment;
import com.klef.jfsd.springboot.model.Forum;
import com.klef.jfsd.springboot.model.ForumDTO;
import com.klef.jfsd.springboot.model.Users;
import com.klef.jfsd.springboot.repo.ForumRepository;
import com.klef.jfsd.springboot.repo.UserRepository;

@Service
public class ForumServiceImpl implements ForumService {

    @Autowired
    private ForumRepository forumRepo;

    @Autowired
    private UserRepository userRepository;

    // Add Forum Post with User Mapping
    public void addForum(ForumDTO forumData) {
        Users user = userRepository.findByEmail(forumData.getFemail());
        
        Forum forum = new Forum();
        
            forum.setUser(user);
            forum.setDate(new Date(System.currentTimeMillis()));
            forum.setTime(new Time(System.currentTimeMillis()));
            forum.setIssue(forumData.getIssue());
            forum.setMsg(forumData.getMsg());
            forumRepo.save(forum);
    }

    // Like or Unlike
    public Forum updateLikes(int forumId, boolean like) {
        Forum forum = forumRepo.findById(forumId).orElseThrow();
        if (like) {
            forum.setLikes(forum.getLikes() + 1);
        } else {
            forum.setLikes(Math.max(forum.getLikes() - 1, 0));
        }
        return forumRepo.save(forum);
    }

    // List All Forums
    public List<Forum> getAllForums() {
        return forumRepo.findAll();
    }

	@Override
	public Comment addComment(Comment comment) {
		// TODO Auto-generated method stub
		return null;
	}
}
