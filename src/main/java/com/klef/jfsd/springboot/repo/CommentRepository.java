package com.klef.jfsd.springboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.klef.jfsd.springboot.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
