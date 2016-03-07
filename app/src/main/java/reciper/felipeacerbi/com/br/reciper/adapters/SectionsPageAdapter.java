package reciper.felipeacerbi.com.br.reciper.adapters;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
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
public class SectionsPageAdapter extends FragmentPagerAdapter {

    public static int TAB_NOT_SELECTED_COLOR = 100;
    public static int TAB_SELECTED_COLOR = 255;

    private final ViewPager viewPager;
    private final TabLayout tabLayout;
    private FragmentManager fragmentManager;
    private AppCompatActivity activity;
    private List<Fragment> fragmentList;

    public SectionsPageAdapter(AppCompatActivity activity, FragmentManager fragmentManager, ViewPager viewPager, final TabLayout tabLayout) {
        super(fragmentManager);
        this.activity = activity;
        this.fragmentManager = fragmentManager;
        this.viewPager = viewPager;
        this.tabLayout = tabLayout;

        fragmentList = new ArrayList<>();
        fragmentList.add(RecipesFragment.newInstance(1));
        fragmentList.add(CartFragment.newInstance(2));
        fragmentList.add(HistoryFragment.newInstance(3));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                TabLayout.Tab tab = tabLayout.getTabAt(position);
                tab.getIcon().setAlpha(TAB_SELECTED_COLOR);
                fragmentList.get(position).onResume();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        return fragmentList.get(position);
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
