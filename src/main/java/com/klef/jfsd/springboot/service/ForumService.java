package com.klef.jfsd.springboot.service;

import java.util.List;

import com.klef.jfsd.springboot.model.Comment;
import com.klef.jfsd.springboot.model.Forum;
import com.klef.jfsd.springboot.model.ForumDTO;

public interface ForumService {
	public void addForum(ForumDTO forumData);
	public Forum updateLikes(int forumId, boolean like);
	public Comment addComment(Comment comment);
	public List<Forum> getAllForums();
}
