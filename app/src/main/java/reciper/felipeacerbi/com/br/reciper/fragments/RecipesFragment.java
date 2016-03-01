package reciper.felipeacerbi.com.br.reciper.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import reciper.felipeacerbi.com.br.reciper.R;
import reciper.felipeacerbi.com.br.reciper.adapters.RecipesAdapter;
import reciper.felipeacerbi.com.br.reciper.models.Recipe;

/**
 * Created by Felipe on 2/29/2016.
 */
public class RecipesFragment extends Fragment {

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final int SPAN_COUNT = 2;
    private RecyclerView recyclerView;
    private TextView emptyText;
    private LayoutManagerType currentLayoutManagerType;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    public RecipesFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static RecipesFragment newInstance(int sectionNumber) {
        RecipesFragment fragment = new RecipesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();

        Recipe newRecipe = new Recipe();
        newRecipe.setName("Cake");
        newRecipe.setDescription("Chocolate strawberry cake.");

        List<Recipe> recipes = new ArrayList<>();
        recipes.add(newRecipe);

        RecipesAdapter recipesAdapter = new RecipesAdapter((AppCompatActivity) getActivity(), recipes);

        recyclerView.setAdapter(recipesAdapter);
        checkEmptyList(recipesAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View recipesList = inflater.inflate(R.layout.fragment_recipes, container, false);

        recyclerView = (RecyclerView) recipesList.findViewById(R.id.all_collections);
        emptyText = (TextView) recipesList.findViewById(R.id.empty_text);

        currentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        setRecyclerViewLayoutManager(currentLayoutManagerType);

        return recipesList;
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
}
