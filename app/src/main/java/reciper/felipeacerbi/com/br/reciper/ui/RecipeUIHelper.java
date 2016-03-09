package reciper.felipeacerbi.com.br.reciper.ui;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.io.File;
import reciper.felipeacerbi.com.br.reciper.R;
import reciper.felipeacerbi.com.br.reciper.activities.NewRecipeActivity;
import reciper.felipeacerbi.com.br.reciper.models.Recipe;

/**
 * Created by felipe.acerbi on 30/10/2015.
 */
public class RecipeUIHelper {


    private NewRecipeActivity newRecipeActivity;
    private EditText nameField;
    private EditText descField;
    private ImageView photo;
    private String path;
    private String extPath;
    private long id;
    private boolean isModify;
    private TextView saveButton;
    private Recipe recipe;
    private LinearLayout photoButton;
    private int position;
    private FloatingActionButton fab;

    public RecipeUIHelper(NewRecipeActivity newRecipeActivity) {

        this.newRecipeActivity = newRecipeActivity;

        getInfo();
        checkFolder();

        isModify = getModify();
        if(isModify) {
            saveButton.setText("UPDATE");
        }
    }

    public void getInfo() {

        saveButton = (TextView) newRecipeActivity.findViewById(R.id.save_button);
        nameField = (EditText) newRecipeActivity.findViewById(R.id.recipe_name);
        descField = (EditText) newRecipeActivity.findViewById(R.id.recipe_description);
        photo = (ImageView) newRecipeActivity.findViewById(R.id.recipe_image);
        //photoButton = (LinearLayout) newRecipeActivity.findViewById(R.id.button_edit_recipe_image);
        fab = (FloatingActionButton) newRecipeActivity.findViewById(R.id.fab);

//        newRecipeActivity.registerForContextMenu(photoButton);
//        photoButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                newRecipeActivity.openContextMenu(v);
//            }
//        });
    }

    public boolean checkFolder() {
        File defaultFolder = new File(Environment.getExternalStoragePublicDirectory(Environment.getExternalStorageState()), "Colladdict");

        if(!defaultFolder.exists()) {
            return defaultFolder.mkdir();
        }

        return false;
    }

    public boolean getModify() {

        Recipe recipe = (Recipe) newRecipeActivity.getIntent().getSerializableExtra("collection_item");

        if(recipe != null) {

            id = recipe.getId();
            nameField.setText(recipe.getName());
            descField.setText(recipe.getDescription());

            if(recipe.getPhotoPath() != null) {
                setPhoto(recipe.getPhotoPath());
            }

            return true;
        }

        return false;
    }

    public Recipe getRecipe() {

        Recipe item = new Recipe();
        item.setId(id);
        item.setName(nameField.getText().toString());
        item.setDescription(descField.getText().toString());
        item.setPhotoPath(getPath());

        return item;

    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtPath() {
        return extPath;
    }

    public void setExtPath(String extPath) {
        this.extPath = extPath;
    }

    public void setPhoto(String path) {
        setPath(path);

        Glide.with(newRecipeActivity)
                .load(getPath())
                .centerCrop()
                .error(R.drawable.cake)
                .into(photo);
    }

    public String getBitmapPath(Intent data){
        Uri selectedImage = data.getData();
        String[] filePathColumn = { MediaStore.Images.Media.DATA };
        Cursor cursor = newRecipeActivity.getContentResolver().query(selectedImage,filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();

        return picturePath;
    }


    public boolean isModify() {
        return isModify;
    }

}
