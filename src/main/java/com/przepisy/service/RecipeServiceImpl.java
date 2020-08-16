package com.przepisy.service;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.przepisy.model.Recipe;
import com.przepisy.model.User;
import com.przepisy.repository.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService{
    
    @Autowired
    private RecipeRepository recipeRepository;
    
    @Autowired
    private UserService userService;
    
	@Override
	public String userRecipies(User user) {
		String recipies = "";
		if(user.getRecipies() != null) {
			for(Recipe recipe: user.getRecipies())
			{	
				recipies+=recipe.toString();
			}
		}
		return recipies;
	}

	@Override
	public User getUser() {
		String username;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
			} else {
			username = principal.toString();
			}
		return userService.findByUsername(username); // caly user ze wszystkimi jego parametrami.
	}

	@Override
	public Set<Recipe> getRecipies() {
		User user = this.getUser();
		Long id = user.getId();
		return recipeRepository.findAllByUserId(id);
	}
	
    @Override
    public void save(Recipe recipe) {
    	User user = this.getUser();
    	recipe.setUser(user);
        recipeRepository.saveAndFlush(recipe);
    }

	@Override
	public List<Recipe> getAllRecipies() {
		return recipeRepository.findAll();
	}

	@Override
	public Set<Recipe> getRecipeById(Long id) {
		
		return recipeRepository.findAllById(id);
	}

	@Override
	public Recipe getOneRecipeById(Long id) {
		return recipeRepository.getRecipeById(id);
	}
}
