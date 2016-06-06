package com.finder.gettingstarted.gettingstarted.RecyclerView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.finder.gettingstarted.gettingstarted.R;

import java.util.ArrayList;

public class CardViewDemoActivity extends AppCompatActivity {

    RecyclerView mRecipeRecyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Recipe> recipeList = new ArrayList<Recipe>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardviewdemo);
        int count = 0;
        for (String name : Recipes.names){
            Recipe newRecipe = new Recipe(Recipes.resourceIds[count], name);
            count ++;
            recipeList.add(newRecipe);
        }

        mRecipeRecyclerView = (RecyclerView) findViewById(R.id.recipe_recyclerview);
        layoutManager = new LinearLayoutManager(this);
        mRecipeRecyclerView.setLayoutManager(layoutManager);
        adapter = new RecipeAdapter(recipeList);
        mRecipeRecyclerView.setAdapter(adapter);
    }
}
