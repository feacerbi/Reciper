package reciper.felipeacerbi.com.br.reciper.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;

import reciper.felipeacerbi.com.br.reciper.fragments.CartFragment;
import reciper.felipeacerbi.com.br.reciper.fragments.HistoryFragment;
import reciper.felipeacerbi.com.br.reciper.fragments.RecipesFragment;

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
        int test = 10;
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
