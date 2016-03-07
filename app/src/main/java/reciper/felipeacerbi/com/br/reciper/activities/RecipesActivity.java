package reciper.felipeacerbi.com.br.reciper.activities;

import android.support.design.widget.TabLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import reciper.felipeacerbi.com.br.reciper.R;
import reciper.felipeacerbi.com.br.reciper.adapters.SectionsPageAdapter;
import reciper.felipeacerbi.com.br.reciper.models.Cart;

public class RecipesActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private SectionsPageAdapter sectionsPageAdapter;
    private Cart cart;
    private FloatingActionButton fab;
    private TabLayout.Tab recipesTab;
    private TabLayout.Tab cartTab;
    private TabLayout.Tab historyTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        cart = new Cart();

        setToolbar();

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

    }

    public void setToolbar() {
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        sectionsPageAdapter = new SectionsPageAdapter(this, getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(sectionsPageAdapter);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

//        recipesTab = createTab(R.drawable.ic_import_contacts_white_24dp, 0);
//        cartTab = createTab(R.drawable.ic_shopping_cart_white_24dp, 1);
//        historyTab = createTab(R.drawable.ic_history_white_24dp, 2);
        //selectTab();
    }

//    public TabLayout.Tab createTab(int iconResource, int position) {
//        TabLayout.Tab tab = tabLayout.getTabAt(position);
//        tab.setIcon(iconResource).getIcon().setAlpha(TAB_NOT_SELECTED_COLOR);
//        return tab;
//    }

    public void selectTab() {
        tabLayout.getTabAt(tabLayout.getSelectedTabPosition()).getIcon().setAlpha(TAB_SELECTED_COLOR);
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recipes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
