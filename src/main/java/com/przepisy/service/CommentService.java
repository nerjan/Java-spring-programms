package com.przepisy.service;

import java.util.Set;

import com.przepisy.model.Comment;
import com.przepisy.model.Recipe;

public interface CommentService {

    Comment save(Comment comment, Recipe recipe);
    Set<Comment> getCommentByRecipeId(Long id);
}