package com.przepisy.service;

import java.util.List;
import java.util.Set;
import com.przepisy.model.Recipe;
import com.przepisy.model.User;

public interface RecipeService {
	String userRecipies(User user);
	User getUser();
	Set<Recipe> getRecipies();
	void save(Recipe recipe);
	List<Recipe> getAllRecipies();
	Set<Recipe> getRecipeById(Long id);
	Recipe getOneRecipeById(Long id);
}
