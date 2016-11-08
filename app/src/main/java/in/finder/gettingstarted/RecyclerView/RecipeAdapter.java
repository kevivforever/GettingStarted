package in.finder.gettingstarted.RecyclerView;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import in.finder.gettingstarted.R;

/**
 * Created by keviv on 05/06/2016.
 */
public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private Context mContext;
    private ArrayList<Recipe> recipes = new ArrayList<Recipe>();
    private CardViewDemoActivity cardViewDemoActivity;

    public RecipeAdapter(ArrayList<Recipe> recipes, Context context) {
        this.recipes = recipes;
        this.mContext = context;
        cardViewDemoActivity = (CardViewDemoActivity) context;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_cardviewdemo, parent, false);
        return new RecipeViewHolder(view, cardViewDemoActivity);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        holder.mRecipeImage.setImageResource(recipe.getImage_id());
        holder.mRecipeName.setText(recipe.getName());
        if (cardViewDemoActivity.isInActionMode) {
            holder.mCBRecipeSelect.setVisibility(View.VISIBLE);
            holder.mCBRecipeSelect.setChecked(false);
        } else {
            holder.mCBRecipeSelect.setVisibility(View.GONE);
        }
        holder.mCardView.setOnLongClickListener(cardViewDemoActivity);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public void updateAdapter(ArrayList<Recipe> list){
        for (Recipe recipe: list){
            recipes.remove(recipe);
        }
        notifyDataSetChanged();
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView mRecipeImage;
        TextView mRecipeName;
        CheckBox mCBRecipeSelect;
        CardViewDemoActivity mCardViewDemoActivity;
        CardView mCardView;

        public RecipeViewHolder(View itemView, CardViewDemoActivity cardViewDemoActivity) {
            super(itemView);
            mCardViewDemoActivity = cardViewDemoActivity;
            mRecipeImage = (ImageView) itemView.findViewById(R.id.recipeImageView);
            mRecipeName = (TextView) itemView.findViewById(R.id.recipeNameTextView);
            mCBRecipeSelect = (CheckBox) itemView.findViewById(R.id.recipe_cb_select);
            mCardView = (CardView) itemView.findViewById(R.id.recipe_cardview);
            mCBRecipeSelect.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mCardViewDemoActivity.prepareSelection(v, getAdapterPosition());
        }
    }
}
