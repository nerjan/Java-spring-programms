package com.przepisy.controller;


import java.sql.SQLException;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.przepisy.model.Comment;
import com.przepisy.model.Recipe;
import com.przepisy.service.CommentService;
import com.przepisy.service.RecipeService;

@Controller
public class RecipiesController {
    @Autowired
    private RecipeService recipeService;
    
    @Autowired
    private CommentService commentService;
    
    
    @GetMapping("/recipies")
    public String recipies(Model model) {
        model.addAttribute("recipeForm", new Recipe());
    	model.addAttribute("recipeList", recipeService.getRecipies());
        return "recipies";
    }
    @PostMapping("/recipies")
    public String addRecipies(@RequestParam("name") String name,
    		@RequestParam("description") String description, 
    		@RequestParam("foodKind") String foodKind,
    		@RequestParam("lvl") String lvl,
    		@RequestParam("time") String time
    		)throws SerialException, SQLException {
    	Recipe recipe = new Recipe();
    	recipe.setName(name);
    	recipe.setDescription(description);
    	recipe.setFoodKind(foodKind);
    	recipe.setLvl(lvl);
    	recipe.setTime(time);
        recipeService.save(recipe);

        return "redirect:/recipies";
    }
    
    @RequestMapping(value = "/recipe/{id}", method = RequestMethod.GET)
    public String getPostWithId(@PathVariable Long id,  Model model) {
    	model.addAttribute("recipeById", recipeService.getRecipeById(id).iterator().next());
    	model.addAttribute("commentToRecipe", commentService.getCommentByRecipeId(id));
    	return "recipe";
    	
    }
    
    @RequestMapping(value = "/recipe/{id}", method = RequestMethod.POST)
    public String addPost(@PathVariable Long id,  Model model, @ModelAttribute("commentForm") Comment commentForm) {
    	commentService.save(commentForm, recipeService.getRecipeById(id).iterator().next());
    	return "redirect:/recipe/"+id;
    	
    }	
}
