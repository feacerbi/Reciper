package reciper.felipeacerbi.com.br.reciper;

import android.os.Environment;

/**
 * Created by felipe.acerbi on 08/03/2016.
 */
public class Constants {

    public static final int LOAD_RECIPE = 0;
    //public static final int LOAD_COLLECTION_ITEMS = 1;
    //public static final int LOAD_CATEGORIES = 2;

    public static final int REQUEST_NEW_RECIPE = 100;
    public static final int REQUEST_MODIFY_RECIPE = 101;
    public static final int TAKE_PICTURE = 102;
    public static final int BROWSE = 103;

    public static int TAB_NOT_SELECTED_COLOR = 100;
    public static int TAB_SELECTED_COLOR = 255;
    public static int TABS_NUMBER = 3;

    public static final String DEFAULT_PATH = Environment.getExternalStorageDirectory() + "/Reciper/";


}
