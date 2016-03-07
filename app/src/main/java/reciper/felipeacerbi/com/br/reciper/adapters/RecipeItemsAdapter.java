package reciper.felipeacerbi.com.br.reciper.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import reciper.felipeacerbi.com.br.reciper.R;
import reciper.felipeacerbi.com.br.reciper.interfaces.TaskManager;
import reciper.felipeacerbi.com.br.reciper.models.Ingredient;
import reciper.felipeacerbi.com.br.reciper.models.Recipe;
import reciper.felipeacerbi.com.br.reciper.models.RecipeItem;

/**
 * Created by felipe.acerbi on 28/09/2015.
 */
public class RecipeItemsAdapter extends RecyclerView.Adapter<RecipeItemsAdapter.ViewHolder> {

    private final TaskManager tm;
    private final SparseBooleanArray selectedItems;
    private List<RecipeItem> recipeItems;
    private SparseBooleanArray oldSelectedPositions;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView nameField;
        private final TextView descField;
        private final ImageView photoField;
        private final TextView quantField;
        private final TextView unitField;


        public ViewHolder(View itemView) {
            super(itemView);

            nameField = (TextView) itemView.findViewById(R.id.ingredient_name);
            descField = (TextView) itemView.findViewById(R.id.ingredient_description);
            quantField = (TextView) itemView.findViewById(R.id.ingredient_quantity);
            unitField = (TextView) itemView.findViewById(R.id.ingredient_unit);
            photoField = (ImageView) itemView.findViewById(R.id.ingredient_image);

        }

        public TextView getNameField() {
            return nameField;
        }

        public TextView getDescField() {
            return descField;
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
    }

    public RecipeItemsAdapter(TaskManager tm, List<RecipeItem> recipeItems) {
        this.tm = tm;
        this.recipeItems = recipeItems;
        selectedItems = new SparseBooleanArray();
        oldSelectedPositions = new SparseBooleanArray();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("Acerbi", "View parent: " + parent);
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final RecipeItem recipeItem = getRecipeItems().get(position);
        Ingredient ingredient = recipeItem.getIngredient();
        Log.d("Acerbi", "View: " + holder.itemView);

        holder.getNameField().setText(ingredient.getName());
        holder.getDescField().setText(ingredient.getDescription());
        holder.getQuantField().setText(String.valueOf(recipeItem.getQuantity()));
        holder.getUnitField().setText(" " + recipeItem.getUnit());

        if(selectedItems.get(position, false)) {
            holder.itemView.setBackgroundColor(tm.getAppCompatActivity().getResources().getColor(R.color.fadeImage));
        } else {
            holder.itemView.setBackgroundColor(tm.getAppCompatActivity().getResources().getColor(android.R.color.transparent));
        }

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

//        holder.getPhotoField().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (recipe.getPhotoPath() != null)
//                    fullImage(recipe.getPhotoPath());
//            }
//        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tm.isActionMode()) {
                    select(holder.getAdapterPosition());
                } else {
//                    Intent intent = new Intent(activity, NewItemActivity.class);
//                    intent.putExtra("collection_item", item);
//                    intent.putExtra("collection_storage", storage);
//                    intent.putExtra("position", position);
//                    activity.startActivityForResult(intent, Constants.REQUEST_MODIFY_COLLECTION_ITEM);
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tm.getAppCompatActivity().startSupportActionMode(tm.getActionModeCallback());
                select(holder.getAdapterPosition());
                return true;
            }
        });
    }

    public List<RecipeItem> getRecipeItems() {
        return recipeItems;
    }

    @Override
    public int getItemCount() {
        return recipeItems.size();
    }

    public void select(int position) {
        if(selectedItems.get(position, false)) {
            selectedItems.delete(position);
        } else {
            selectedItems.put(position, true);
        }
        notifyItemChanged(position);

        int selectedCount = getSelectedItemsCount();
        tm.getActionMode().setTitle(String.valueOf(selectedCount));
    }

    public void deselectAll() {
        oldSelectedPositions = selectedItems.clone();
        notifyItemsChanged();
        selectedItems.clear();
    }

    public int getSelectedItemsCount(){ return selectedItems.size(); }

    public List<RecipeItem> getSelectedItems() {
        List<RecipeItem> selectedObjects = new ArrayList<>(getSelectedItemsCount());
        for (int i = 0; i < getSelectedItemsCount(); i++) {
            selectedObjects.add(getRecipeItems().get(selectedItems.keyAt(i)));
        }
        return selectedObjects;
    }

    public void notifyItemsRemoved() {
        for(int i = 0; i < getSelectedItemsCount(); i++) {
            int position = selectedItems.keyAt(i);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, getItemCount());
        }
        getRecipeItems().removeAll(getSelectedItems());
    }

    public void notifyItemsInserted(List<RecipeItem> ingredients) {
        for(int i = 0; i < oldSelectedPositions.size(); i++) {
            int position = oldSelectedPositions.keyAt(i);
            getRecipeItems().add(position, ingredients.get(i));
            notifyItemInserted(position);
            notifyItemRangeChanged(position, getItemCount());
        }
    }

    public void notifyNewItemInserted(RecipeItem recipeItem) {
        getRecipeItems().add(recipeItem);
        int position = getItemCount();
        notifyItemInserted(position);
        notifyItemRangeChanged(position, getItemCount());
    }

    public void notifyItemsChanged() {
        for(int i = 0; i < getSelectedItemsCount(); i++) {
            int position = selectedItems.keyAt(i);
            notifyItemChanged(position);
        }
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
