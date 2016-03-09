package reciper.felipeacerbi.com.br.reciper.adapters;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import reciper.felipeacerbi.com.br.reciper.R;
import reciper.felipeacerbi.com.br.reciper.interfaces.TaskManager;
import reciper.felipeacerbi.com.br.reciper.models.Ingredient;
import reciper.felipeacerbi.com.br.reciper.models.RecipeItem;

/**
 * Created by felipe.acerbi on 28/09/2015.
 */
public class NewIngredientAdapter extends RecyclerView.Adapter<NewIngredientAdapter.ViewHolder> {

    private final TaskManager tm;
    private final SparseBooleanArray selectedItems;
    private List<RecipeItem> recipeItems;
    private SparseBooleanArray oldSelectedPositions;
    private List<RecipeItem> deleteList;
    private boolean remove;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView nameField;
        private final ImageView photoField;
        private final TextView quantField;
        private final TextView unitField;
        private final ImageView removeButton;


        public ViewHolder(View itemView) {
            super(itemView);

            nameField = (TextView) itemView.findViewById(R.id.ingredient_name);
            quantField = (TextView) itemView.findViewById(R.id.ingredient_quantity);
            unitField = (TextView) itemView.findViewById(R.id.ingredient_unit);
            photoField = (ImageView) itemView.findViewById(R.id.ingredient_image);
            removeButton = (ImageView) itemView.findViewById(R.id.remove_ingredient_icon);

        }

        public TextView getNameField() {
            return nameField;
        }

        public ImageView getPhotoField() {
            return photoField;
        }

        public TextView getQuantField() {
            return quantField;
        }

        public TextView getUnitField() {
            return unitField;
        }

        public ImageView getRemoveButton() {
            return removeButton;
        }
    }

    public NewIngredientAdapter(TaskManager tm, List<RecipeItem> recipeItems) {
        this.tm = tm;
        this.recipeItems = recipeItems;
        selectedItems = new SparseBooleanArray();
        oldSelectedPositions = new SparseBooleanArray();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_ingredient_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final RecipeItem recipeItem = getRecipeItems().get(position);
        Ingredient ingredient = recipeItem.getIngredient();

        holder.getNameField().setText(ingredient.getName());
        holder.getQuantField().setText(String.valueOf(recipeItem.getQuantity()));
        holder.getUnitField().setText(recipeItem.getUnit());

        if(ingredient.getPhotoPath() != null) {
            if(ingredient.getPhotoPath().equals("chocolate")) {
                Glide.with(tm.getAppCompatActivity())
                        .load(R.drawable.chocolate_bar)
                        .fitCenter()
                        .into(holder.getPhotoField());
            } else {
                Glide.with(tm.getAppCompatActivity())
                        .load(R.drawable.sugar)
                        .fitCenter()
                        .into(holder.getPhotoField());
            }
        } else {
            Glide.with(tm.getAppCompatActivity())
                    .load(R.drawable.sugar)
                    .fitCenter()
                    .into(holder.getPhotoField());
        }

        holder.getRemoveButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select(position);
                deleteList = getSelectedItems();
                getRecipeItems().remove(recipeItem);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, getItemCount());
                remove = true;
                Snackbar.make(tm.getAppCompatActivity().findViewById(R.id.coordinator),
                        recipeItem.getIngredient().getName() + " category removed",
                        Snackbar.LENGTH_LONG)
                        .setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                add(recipeItem, position);
                                remove = false;
                            }
                        }).setCallback(new Snackbar.Callback() {
                    @Override
                    public void onDismissed(Snackbar snackbar, int event) {
                        if (remove) {
                            //new RemoveTask(context).execute(deleteList);
                        }
                        deselectAll();
                        super.onDismissed(snackbar, event);
                    }
                }).show();
            }
        });

//        holder.getPhotoField().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (recipe.getPhotoPath() != null)
//                    fullImage(recipe.getPhotoPath());
//            }
//        });
    }

    public List<RecipeItem> getRecipeItems() {
        return recipeItems;
    }

    public void add(RecipeItem recipeItem, int position) {
        getRecipeItems().add(position, recipeItem);
        notifyItemInserted(position);
        notifyItemRangeChanged(position, getItemCount());
    }

    @Override
    public int getItemCount() {
        return getRecipeItems().size();
    }

    public void select(int position) {
        if(selectedItems.get(position, false)) {
            selectedItems.delete(position);
        } else {
            selectedItems.put(position, true);
        }
    }

    public void deselectAll() {
        notifyItemRangeChanged(0, getItemCount());
        selectedItems.clear();
    }

    public List<RecipeItem> getSelectedItems() {
        List<RecipeItem> selectedObjects = new ArrayList<>(1);
        selectedObjects.add(getRecipeItems().get(selectedItems.keyAt(0)));
        return selectedObjects;
    }

//    public void fullImage(String path) {
//        Dialog mSplashDialog = new Dialog(activity);
//        mSplashDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        mSplashDialog.setContentView(R.layout.image_fullscreen);
//        mSplashDialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        mSplashDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        mSplashDialog.setCancelable(true);
//        Glide.with(activity)
//                .load(path)
//                .fitCenter()
//                .error(R.drawable.shells)
//                .into(((ImageView) mSplashDialog.findViewById(R.id.imageview_fullscreen)));
//        mSplashDialog.show();
//    }
}
