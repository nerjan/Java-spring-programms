package com.przepisy.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.przepisy.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	Set<Comment> findAllByRecipeId(Long id);
}
