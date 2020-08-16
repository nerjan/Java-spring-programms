package com.przepisy.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.przepisy.model.Comment;
import com.przepisy.model.Recipe;
import com.przepisy.model.User;
import com.przepisy.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private RecipeService recipeService;
    
	@Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment save(Comment comment, Recipe recipe) {
    	User user = recipeService.getUser();
    	comment.setUser(user);
    	comment.setRecipe(recipe);
        return commentRepository.saveAndFlush(comment);
    }

	@Override
	public Set<Comment> getCommentByRecipeId(Long id) {
		return commentRepository.findAllByRecipeId(id);
	}
}