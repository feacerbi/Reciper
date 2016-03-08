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
    private TabLayout.Tab recipesTab;
    private TabLayout.Tab cartTab;
    private TabLayout.Tab historyTab;

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

        recipesTab = createTab(0);
        cartTab = createTab(1);
        historyTab = createTab(2);

        tabLayout.setOnTabSelectedListener(this);

        fragmentList.add(RecipesFragment.newInstance(0));
        fragmentList.add(CartFragment.newInstance(1));
        fragmentList.add(HistoryFragment.newInstance(2));

        viewPager.setCurrentItem(1);
    }

    public TabLayout.Tab createTab(int position) {
        TabLayout.Tab tab = tabLayout.getTabAt(position);
        tab.setIcon(tabIcons[position]).getIcon().setAlpha(TAB_NOT_SELECTED_COLOR);
        return tab;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return 3;
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
