package reciper.felipeacerbi.com.br.reciper.adapters;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import reciper.felipeacerbi.com.br.reciper.R;
import reciper.felipeacerbi.com.br.reciper.fragments.CartFragment;
import reciper.felipeacerbi.com.br.reciper.fragments.HistoryFragment;
import reciper.felipeacerbi.com.br.reciper.fragments.RecipesFragment;

/**
 * Created by Felipe on 2/29/2016.
 */
public class SectionsPageAdapter extends FragmentPagerAdapter implements TabLayout.OnTabSelectedListener {

    public static int TAB_NOT_SELECTED_COLOR = 100;
    public static int TAB_SELECTED_COLOR = 255;

    private final ViewPager viewPager;
    private final TabLayout tabLayout;
    private FragmentManager fragmentManager;
    private AppCompatActivity activity;
    private List<Fragment> fragmentList;
    private int[] tabIcons = {
            R.drawable.ic_import_contacts_white_24dp,
            R.drawable.ic_shopping_cart_white_24dp,
            R.drawable.ic_history_white_24dp
    };

    public SectionsPageAdapter(AppCompatActivity activity, FragmentManager fragmentManager, ViewPager viewPager, final TabLayout tabLayout) {
        super(fragmentManager);
        this.activity = activity;
        this.fragmentManager = fragmentManager;
        this.viewPager = viewPager;
        this.tabLayout = tabLayout;

        fragmentList = new ArrayList<>();

        setTabs();
    }

    public void setTabs() {
        viewPager.setAdapter(this);
        tabLayout.setupWithViewPager(viewPager);

        addTab(RecipesFragment.newInstance(1), 0);
        addTab(CartFragment.newInstance(2), 1);
        addTab(HistoryFragment.newInstance(3), 2);

        tabLayout.setOnTabSelectedListener(this);

        viewPager.setCurrentItem(0);
    }

    public void addTab(Fragment fragment, int position) {
        fragmentList.add(position, fragment);
        tabLayout.getTabAt(position).setIcon(tabIcons[position]).getIcon().setAlpha(TAB_NOT_SELECTED_COLOR);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int position = tab.getPosition();
        tab.getIcon().setAlpha(TAB_SELECTED_COLOR);
        fragmentList.get(position).onResume();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        tab.getIcon().setAlpha(TAB_NOT_SELECTED_COLOR);
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
