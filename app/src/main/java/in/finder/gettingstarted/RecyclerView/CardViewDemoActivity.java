package in.finder.gettingstarted.RecyclerView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import in.finder.gettingstarted.R;

public class CardViewDemoActivity extends AppCompatActivity implements View.OnLongClickListener {

    int selectionCount = 0;
    Boolean isInActionMode = false;
    TextView mTextViewCounterText;
    RecyclerView mRecipeRecyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
    ArrayList<Recipe> selectedRecipeList = new ArrayList<>();
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardviewdemo);

        mToolbar = (Toolbar) findViewById(R.id.cardviewdemo_toolbar);
        setSupportActionBar(mToolbar);
        mTextViewCounterText = (TextView) findViewById(R.id.cardviewdemo_text_counter);
        mTextViewCounterText.setVisibility(View.GONE);

        int count = 0;
        for (String name : Recipes.names){
            Recipe newRecipe = new Recipe(Recipes.resourceIds[count], name);
            count ++;
            recipeList.add(newRecipe);
        }
        mRecipeRecyclerView = (RecyclerView) findViewById(R.id.cardviewdemo_recyclerview);
        layoutManager = new LinearLayoutManager(this);
        mRecipeRecyclerView.setLayoutManager(layoutManager);
        mRecipeRecyclerView.setHasFixedSize(true);
        adapter = new RecipeAdapter(recipeList, this);
        mRecipeRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onLongClick(View v) {
        mToolbar.getMenu().clear();
        mToolbar.inflateMenu(R.menu.menu_action_mode);
        mTextViewCounterText.setVisibility(View.VISIBLE);
        isInActionMode = true;
        adapter.notifyDataSetChanged();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return true;
    }

    public void prepareSelection(View view, int position){
        if(((CheckBox)view).isChecked()){
            selectedRecipeList.add(recipeList.get(position));
            selectionCount++;
        }else{
            selectedRecipeList.remove(recipeList.get(position));
            selectionCount--;
        }
        updateCounter(selectionCount);
    }

    public void updateCounter(int count){
        if (count == 0){
            mTextViewCounterText.setText("0 Item Selected");
        }else {
            mTextViewCounterText.setText(count + " Items Selected");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cardviewdemo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_share) {
            return true;
        }else if (id == R.id.action_search){
            return true;
        }else if (id == R.id.action_delete){
            RecipeAdapter recipeAdapter = (RecipeAdapter) adapter;
            recipeAdapter.updateAdapter(selectedRecipeList);
            clearActionMode();
            selectionCount = 0;
            selectedRecipeList.clear();
        }else if (id == android.R.id.home){
            clearActionMode();
            adapter.notifyDataSetChanged();
        }

        return super.onOptionsItemSelected(item);
    }
    public  void clearActionMode(){
        isInActionMode = false;
        mToolbar.getMenu().clear();
        mToolbar.inflateMenu(R.menu.menu_cardviewdemo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        mTextViewCounterText.setVisibility(View.GONE);
        mTextViewCounterText.setText("0 Item Selected");
    }

    @Override
    public void onBackPressed() {
        if (isInActionMode){
            clearActionMode();
            adapter.notifyDataSetChanged();
        }else {
            super.onBackPressed();
        }
    }
}
