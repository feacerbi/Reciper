package reciper.felipeacerbi.com.br.reciper.adapters;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import reciper.felipeacerbi.com.br.reciper.R;
import reciper.felipeacerbi.com.br.reciper.activities.Recipes;
import reciper.felipeacerbi.com.br.reciper.models.Recipe;

/**
 * Created by felipe.acerbi on 28/09/2015.
 */
public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {

    private final AppCompatActivity activity;
//    private final SparseBooleanArray selectedItems;
    private List<Recipe> recipes;
    private SparseBooleanArray oldSelectedPositions;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView titleField;
        private final ImageView photoField;
        private final TextView descField;

        public ViewHolder(View itemView) {
            super(itemView);

            titleField = (TextView) itemView.findViewById(R.id.item_title);
            descField = (TextView) itemView.findViewById(R.id.item_description);
            photoField = (ImageView) itemView.findViewById(R.id.item_image);

        }

        public TextView getTitleField() {
            return titleField;
        }

        public TextView getDescField() {
            return descField;
        }

        public ImageView getPhotoField() {
            return photoField;
        }
    }

    public RecipesAdapter(AppCompatActivity activity, List<Recipe> recipes) {
        this.activity = activity;
        this.recipes = recipes;
//        selectedItems = new SparseBooleanArray();
//        oldSelectedPositions = new SparseBooleanArray();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Recipe recipe = recipes.get(position);

        holder.getTitleField().setText(recipe.getName());
        holder.getDescField().setText(recipe.getDescription());

//        if(selectedItems.get(position, false)) {
//            holder.itemView.setBackgroundColor(activity.getResources().getColor(R.color.fadeImage));
//        } else {
//            holder.itemView.setBackgroundColor(activity.getResources().getColor(android.R.color.transparent));
//        }

        if(recipe.getPhotoPath() != null) {
            Glide.with(activity)
                    .load(recipe.getPhotoPath())
                    .centerCrop()
                    .into(holder.getPhotoField());
        } else {
            Glide.with(activity)
                    .load(R.drawable.cake)
                    .centerCrop()
                    .into(holder.getPhotoField());
        }

//        holder.getPhotoField().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (recipe.getPhotoPath() != null)
//                    fullImage(recipe.getPhotoPath());
//            }
//        });

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (context.isActionMode()) {
//                    select(position);
//                } else {
//                    Intent intent = new Intent(activity, NewItemActivity.class);
//                    intent.putExtra("collection_item", item);
//                    intent.putExtra("collection_storage", storage);
//                    intent.putExtra("position", position);
//                    activity.startActivityForResult(intent, Constants.REQUEST_MODIFY_COLLECTION_ITEM);
//                }
//            }
//        });

//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                activity.startSupportActionMode(context.getActionModeCallback());
//                select(position);
//                return true;
//            }
//        });
    }

    public List<Recipe> getItems() {
        return recipes;
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

//    public void select(int position) {
//        if(selectedItems.get(position, false)) {
//            selectedItems.delete(position);
//        } else {
//            selectedItems.put(position, true);
//        }
//        notifyItemChanged(position);
//
//        int selectedCount = getSelectedItemsCount();
//        context.getActionMode().setTitle(String.valueOf(selectedCount));
//    }
//
//    public void deselectAll() {
//        oldSelectedPositions = selectedItems.clone();
//        notifyItemsChanged();
//        selectedItems.clear();
//    }
//
//    public int getSelectedItemsCount(){ return selectedItems.size(); }
//
//    public List<CollectionItem> getSelectedItems() {
//        List<CollectionItem> selectedObjects = new ArrayList<>(getSelectedItemsCount());
//        for (int i = 0; i < getSelectedItemsCount(); i++) {
//            selectedObjects.add(items.get(selectedItems.keyAt(i)));
//        }
//        return selectedObjects;
//    }
//
//    public void notifyItemsRemoved() {
//        for(int i = 0; i < getSelectedItemsCount(); i++) {
//            int position = selectedItems.keyAt(i);
//            notifyItemRemoved(position);
//            notifyItemRangeChanged(position, getItemCount());
//        }
//        getItems().removeAll(getSelectedItems());
//    }
//
//    public void notifyItemsInserted(List<CollectionItem> collectionItems) {
//        for(int i = 0; i < oldSelectedPositions.size(); i++) {
//            int position = oldSelectedPositions.keyAt(i);
//            getItems().add(position, collectionItems.get(i));
//            notifyItemInserted(position);
//            notifyItemRangeChanged(position, getItemCount());
//        }
//    }
//
//    public void notifyNewItemInserted(CollectionItem collectionItem) {
//        getItems().add(collectionItem);
//        int position = getItemCount();
//        notifyItemInserted(position);
//        notifyItemRangeChanged(position, getItemCount());
//    }
//
//    public void notifyItemsChanged() {
//        for(int i = 0; i < getSelectedItemsCount(); i++) {
//            int position = selectedItems.keyAt(i);
//            notifyItemChanged(position);
//        }
//    }
//
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
