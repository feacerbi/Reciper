package reciper.felipeacerbi.com.br.reciper.adapters;

import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.widget.Toast;

import reciper.felipeacerbi.com.br.reciper.R;
import reciper.felipeacerbi.com.br.reciper.activities.Recipes;
import reciper.felipeacerbi.com.br.reciper.fragments.CartFragment;
import reciper.felipeacerbi.com.br.reciper.fragments.HistoryFragment;
import reciper.felipeacerbi.com.br.reciper.fragments.RecipesFragment;
import reciper.felipeacerbi.com.br.reciper.interfaces.TaskManager;

/**
 * Created by Felipe on 2/29/2016.
 */
public class SectionsPageAdapter extends FragmentPagerAdapter {

    private FragmentManager fragmentManager;
    private AppCompatActivity activity;

    public SectionsPageAdapter(AppCompatActivity activity, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.activity = activity;
        this.fragmentManager = fragmentManager;
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
        return null;
    }
}
