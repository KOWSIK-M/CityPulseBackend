package com.klef.jfsd.springboot.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import com.klef.jfsd.springboot.model.Forum;

public interface ForumRepository extends JpaRepository<Forum, Integer> {
}
