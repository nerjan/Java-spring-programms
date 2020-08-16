package com.przepisy.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.przepisy.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long>{
	Recipe getRecipeById(Long id);
	Set<Recipe> findAllByUserId(Long id);
	Set<Recipe> findAllById(Long id);
}
