package reciper.felipeacerbi.com.br.reciper.interfaces;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import reciper.felipeacerbi.com.br.reciper.app.ReciperApplication;

/**
 * Created by felipe.acerbi on 17/02/2016.
 */
public interface TaskManager {

    AppCompatActivity getAppCompatActivity();

    ReciperApplication getApp();

    ActionMode getActionMode();

    boolean isActionMode();

    ActionMode.Callback getActionModeCallback();
}
