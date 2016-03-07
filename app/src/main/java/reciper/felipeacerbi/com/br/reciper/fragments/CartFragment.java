package reciper.felipeacerbi.com.br.reciper.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import reciper.felipeacerbi.com.br.reciper.R;
import reciper.felipeacerbi.com.br.reciper.activities.RecipesActivity;
import reciper.felipeacerbi.com.br.reciper.adapters.RecipeItemsAdapter;
import reciper.felipeacerbi.com.br.reciper.app.ReciperApplication;
import reciper.felipeacerbi.com.br.reciper.interfaces.TaskManager;
import reciper.felipeacerbi.com.br.reciper.models.Ingredient;
import reciper.felipeacerbi.com.br.reciper.models.RecipeItem;
import reciper.felipeacerbi.com.br.reciper.models.Unit;

/**
 * Created by Felipe on 2/29/2016.
 */
public class CartFragment extends Fragment implements ActionMode.Callback, TaskManager {

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final int SPAN_COUNT = 2;
    private RecyclerView recyclerView;
    private TextView emptyText;
    private LayoutManagerType currentLayoutManagerType;
    private RecipeItemsAdapter recipeItemsAdapter;
    private ActionMode actionMode;
    private boolean isActionMode;
    private List<RecipeItem> deleteList;
    private boolean remove;
    private FloatingActionButton fab;
    private RecipesActivity recipesActivity;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    public CartFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static CartFragment newInstance(int sectionNumber) {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipesActivity = (RecipesActivity) getAppCompatActivity();
        isActionMode = false;
    }

    @Override
    public void onStart() {
        super.onStart();

        Ingredient ingredient = new Ingredient("Chocolate Bar", "Milk chocolate bar");
        ingredient.setPhotoPath("chocolate");
        Ingredient ingredient2 = new Ingredient("Sugar", "Refined sugar");
        ingredient.setPhotoPath("sugar");

        RecipeItem recipeItem = new RecipeItem(ingredient, 200, Unit.OZ);
        RecipeItem recipeItem2 = new RecipeItem(ingredient2, 150, Unit.MILLILITER);

        recipesActivity.getCart().getRecipeItems().add(recipeItem);
        recipesActivity.getCart().getRecipeItems().add(recipeItem2);

        recipeItemsAdapter = new RecipeItemsAdapter(this, recipesActivity.getCart().getRecipeItems());

        recyclerView.setAdapter(recipeItemsAdapter);
        checkEmptyList(recipeItemsAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        fab.setImageResource(R.drawable.ic_done_white_24dp);
        fab.show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View ingredientsList = inflater.inflate(R.layout.fragment_cart, container, false);

        recyclerView = (RecyclerView) ingredientsList.findViewById(R.id.all_items);
        emptyText = (TextView) ingredientsList.findViewById(R.id.empty_text);
        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy > 0) {
                    fab.hide();
                } else {
                    fab.show();
                }
            }
        });

        /* API 23
        recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(scrollY > oldScrollY) {
                    fab.show();
                } else {
                    fab.hide();
                }
            }
        }); */

        currentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        setRecyclerViewLayoutManager(currentLayoutManagerType);

        return ingredientsList;
    }

    public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;

        if (recyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) recyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        RecyclerView.LayoutManager layoutManager = null;
        switch (layoutManagerType) {
            case GRID_LAYOUT_MANAGER:
                layoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
                currentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                //if(layoutMenuItem != null) layoutMenuItem.setIcon(R.drawable.ic_view_stream_white_24dp);
                break;
            case LINEAR_LAYOUT_MANAGER:
                layoutManager = new LinearLayoutManager(getActivity());
                currentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                //if(layoutMenuItem != null) layoutMenuItem.setIcon(R.drawable.ic_view_module_white_24dp);
                break;
            default:
                layoutManager = new LinearLayoutManager(getActivity());
                currentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        }

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.scrollToPosition(scrollPosition);
    }

    public void checkEmptyList(RecyclerView.Adapter adapter) {
        if(adapter.getItemCount() == 0) {
            emptyText.setVisibility(View.VISIBLE);
        } else {
            emptyText.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        MenuInflater inflater = mode.getMenuInflater();
        inflater.inflate(R.menu.recipes_action_mode_menu, menu);

        recipeItemsAdapter = (RecipeItemsAdapter) recyclerView.getAdapter();
        actionMode = mode;

        mode.setTitle(String.valueOf(1));
        isActionMode = true;
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        // TODO Deprecated method for newer versions.
//       context.getWindow().setStatusBarColor(context.getResources().getColor(R.color.colorAccentDark, null));
        getActivity().getWindow().setStatusBarColor(getActivity().getResources().getColor(R.color.colorAccentDark));
        return true;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_remove_collection:
                deleteList = recipeItemsAdapter.getSelectedItems();
                recipeItemsAdapter.notifyItemsRemoved();
                remove = true;
                Snackbar.make(getView().findViewById(R.id.all_recipes), getResources().getString(R.string.recipes_removed_snackbar), Snackbar.LENGTH_LONG)
                        .setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                recipeItemsAdapter.notifyItemsInserted(deleteList);
                                remove = false;
                            }
                        }).setCallback(new Snackbar.Callback() {
                    @Override
                    public void onDismissed(Snackbar snackbar, int event) {
                        if(remove) {
                            //new RemoveTask(CollectionStorageFragment.this).execute(deleteList);
                        }
                        super.onDismissed(snackbar, event);
                    }
                }).show();
                mode.finish();
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        // TODO Deprecated method for newer versions.
//        context.getWindow().setStatusBarColor(context.getResources().getColor(R.color.colorPrimaryDark, null));
        getActivity().getWindow().setStatusBarColor(getActivity().getResources().getColor(R.color.colorPrimaryDark));
        recipeItemsAdapter.deselectAll();
        isActionMode = false;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_recipes, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
//            case R.id.action_change_layout:
//                layoutMenuItem = item;
//                currentLayoutManagerType = (currentLayoutManagerType == LayoutManagerType.LINEAR_LAYOUT_MANAGER) ?
//                        LayoutManagerType.GRID_LAYOUT_MANAGER : LayoutManagerType.LINEAR_LAYOUT_MANAGER;
//                setRecyclerViewLayoutManager(currentLayoutManagerType);
//                return true;
            case R.id.action_settings:
                return true;
        }

        return true;
    }

    @Override
    public AppCompatActivity getAppCompatActivity() {
        return (AppCompatActivity) getActivity();
    }

    @Override
    public ReciperApplication getApp() {
        return (ReciperApplication) getActivity().getApplication();
    }

    @Override
    public ActionMode getActionMode() {
        return actionMode;
    }

    @Override
    public boolean isActionMode() {
        return isActionMode;
    }

    @Override
    public ActionMode.Callback getActionModeCallback() {
        return this;
    }
}
