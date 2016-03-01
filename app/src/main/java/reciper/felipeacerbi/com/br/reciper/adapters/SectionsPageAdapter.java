package reciper.felipeacerbi.com.br.reciper.adapters;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import reciper.felipeacerbi.com.br.reciper.R;
import reciper.felipeacerbi.com.br.reciper.activities.Recipes;
import reciper.felipeacerbi.com.br.reciper.fragments.CartFragment;
import reciper.felipeacerbi.com.br.reciper.fragments.HistoryFragment;
import reciper.felipeacerbi.com.br.reciper.fragments.RecipesFragment;

/**
 * Created by Felipe on 2/29/2016.
 */
public class SectionsPageAdapter extends FragmentPagerAdapter {

    FragmentManager fm;
    AppCompatActivity activity;

    public SectionsPageAdapter(AppCompatActivity activity, FragmentManager fm) {
        super(fm);
        this.activity = activity;
        this.fm = fm;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        switch (position) {
            case 0:
                return RecipesFragment.newInstance(position + 1);
            case 1:
                return CartFragment.newInstance(position + 1);
            case 2:
                return HistoryFragment.newInstance(position + 1);
            default:
                return RecipesFragment.newInstance(position + 1);
        }
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return activity.getResources().getString(R.string.tab_name_recipes);
            case 1:
                return activity.getResources().getString(R.string.tab_name_cart);
            case 2:
                return activity.getResources().getString(R.string.tab_name_history);
            default:
                return null;
        }
    }

}
