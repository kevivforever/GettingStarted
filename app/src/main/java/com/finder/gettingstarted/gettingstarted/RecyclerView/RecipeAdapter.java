package com.finder.gettingstarted.gettingstarted.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.finder.gettingstarted.gettingstarted.R;

import java.util.ArrayList;

/**
 * Created by keviv on 05/06/2016.
 */
public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    ArrayList<Recipe> recipes = new ArrayList<Recipe>();

    public RecipeAdapter(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_layout, parent, false);
        RecipeViewHolder recipeViewHolder = new RecipeViewHolder(view);
        return recipeViewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        holder.mRecipeImage.setImageResource(recipe.getImage_id());
        holder.mRecipeName.setText(recipe.getName());
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }


    public static class RecipeViewHolder extends RecyclerView.ViewHolder{

        ImageView mRecipeImage;
        TextView mRecipeName;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            mRecipeImage = (ImageView) itemView.findViewById(R.id.recipeImageView);
            mRecipeName = (TextView) itemView.findViewById(R.id.recipeNameTextView);
        }
    }
}
