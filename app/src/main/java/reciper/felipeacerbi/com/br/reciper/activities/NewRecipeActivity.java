package reciper.felipeacerbi.com.br.reciper.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;

import reciper.felipeacerbi.com.br.reciper.Constants;
import reciper.felipeacerbi.com.br.reciper.R;
import reciper.felipeacerbi.com.br.reciper.adapters.RecipeItemsAdapter;
import reciper.felipeacerbi.com.br.reciper.interfaces.TaskManager;
import reciper.felipeacerbi.com.br.reciper.models.Recipe;

/**
 * Created by felipe.acerbi on 01/10/2015.
 */
public class NewRecipeActivity extends AppCompatActivity {

    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;

    private Recipe recipe;
    private RecyclerView recyclerView;
    private TextView emptyText;
    private LinearLayoutManager layoutManager;
    private Bundle savedInstanceState;
    private RecipeItemsAdapter recipeItemsAdapter;
    private View view;
    private ImageView recipePhoto;
    private FloatingActionButton fab;
    private TextView recipeTitle;
    private TextView recipeDesc;
    private LinearLayout scrim;
    private CollapsingToolbarLayout collapToolbar;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private LayoutManagerType currentLayoutManagerType;
    private ActionMode actionMode;
    private boolean remove;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);

        //this.savedInstanceState = savedInstanceState;

        Intent intent = getIntent();
        if(intent != null && intent.getExtras() != null) {
            recipe = (Recipe) intent.getExtras().get("recipe");
        } else {
            recipe = new Recipe();
        }

        //getWindow().getEnterTransition().addListener(new TransitionsListener(this, recyclerView, emptyText, Constants.LOAD_COLLECTION_ITEMS, storage));

        setToolbar();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();

        //getWindow().setEnterTransition(new Fade());

        fab = (FloatingActionButton) findViewById(R.id.fab);
        recipePhoto = (ImageView) findViewById(R.id.recipe_image);
        recipeTitle = (TextView) findViewById(R.id.recipe_name);
        recipeDesc = (TextView) findViewById(R.id.recipe_description);
        recyclerView = (RecyclerView) findViewById(R.id.recipe_ingredients);
        emptyText = (TextView) findViewById(R.id.empty_text);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Add ingredient to recipe.
            }
        });

        currentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

//        if (savedInstanceState != null) {
//            currentLayoutManagerType = (LayoutManagerType) savedInstanceState
//                    .getSerializable(KEY_LAYOUT_MANAGER);
//        }
        setRecyclerViewLayoutManager(currentLayoutManagerType);
    }

    public void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        collapsingToolbarLayout.setTitle(recipe.getName());
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if (recipe.getPhotoPath() != null) {
            Glide.with(this)
                    .load(recipe.getPhotoPath())
                    .centerCrop()
                    .error(R.drawable.cake)
                    .into(recipePhoto);
        } else {
            Glide.with(this)
                    .load(R.drawable.cake)
                    .centerCrop()
                    .error(R.drawable.cake)
                    .into(recipePhoto);
        }
    }

    @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Constants.REQUEST_MODIFY_RECIPE) {
                recipe = (Recipe) data.getExtras().getSerializable("recipe");
                setToolbar();
                Snackbar.make(
                        findViewById(R.id.coordinator),
                        recipe.getName() + " recipe modified",
                        Snackbar.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recipes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_edit_recipe:
                break;
            case R.id.action_settings:
                break;
        }

        return true;
    }

    public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;

        if (recyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) recyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        switch (layoutManagerType) {
            case GRID_LAYOUT_MANAGER:
                layoutManager = new GridLayoutManager(this, SPAN_COUNT);
                currentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;
            case LINEAR_LAYOUT_MANAGER:
                layoutManager = new LinearLayoutManager(this);
                currentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;
            default:
                layoutManager = new LinearLayoutManager(this);
                currentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        }

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.scrollToPosition(scrollPosition);
    }

    @Override
    public void onBackPressed() {
        supportFinishAfterTransition();
    }
}
