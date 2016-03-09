package reciper.felipeacerbi.com.br.reciper.adapters;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import reciper.felipeacerbi.com.br.reciper.Constants;
import reciper.felipeacerbi.com.br.reciper.R;
import reciper.felipeacerbi.com.br.reciper.fragments.CartFragment;
import reciper.felipeacerbi.com.br.reciper.fragments.HistoryFragment;
import reciper.felipeacerbi.com.br.reciper.fragments.RecipesFragment;

/**
 * Created by Felipe on 2/29/2016.
 */
public class SectionsPageAdapter extends FragmentPagerAdapter implements TabLayout.OnTabSelectedListener {

    private final FloatingActionButton fab;
    private FragmentManager fragmentManager;
    private List<Fragment> fragmentList;
    private AppCompatActivity activity;
    private int[] tabIcons = {
            R.drawable.ic_import_contacts_white_24dp,
            R.drawable.ic_shopping_cart_white_24dp,
            R.drawable.ic_history_white_24dp
    };
    private int currentTab;

    public SectionsPageAdapter(AppCompatActivity activity, FragmentManager fragmentManager, FloatingActionButton fab) {
        super(fragmentManager);
        this.activity = activity;
        this.fragmentManager = fragmentManager;
        this.fab = fab;

        fragmentList = new ArrayList<>();
        fragmentList.add(RecipesFragment.newInstance(1));
        fragmentList.add(CartFragment.newInstance(2));
        fragmentList.add(HistoryFragment.newInstance(3));
    }

    public void setIcons(TabLayout tabLayout) {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]).getIcon().setAlpha(Constants.TAB_NOT_SELECTED_COLOR);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]).getIcon().setAlpha(Constants.TAB_NOT_SELECTED_COLOR);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]).getIcon().setAlpha(Constants.TAB_NOT_SELECTED_COLOR);
    }

    public int getCurrentTab() {
        return currentTab;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int position = tab.getPosition();
        switch (position) {
            case 0:
                fab.setImageResource(R.drawable.plus_sign);
                fab.show();
                break;
            case 1:
                fab.setImageResource(R.drawable.ic_done_white_24dp);
                fab.show();
                break;
            case 2:
                fab.hide();
        }
        tab.getIcon().setAlpha(Constants.TAB_SELECTED_COLOR);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        tab.getIcon().setAlpha(Constants.TAB_NOT_SELECTED_COLOR);
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
