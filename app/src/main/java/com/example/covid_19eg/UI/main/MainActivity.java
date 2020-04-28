package com.example.covid_19eg.UI.main;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.example.covid_19eg.R;
import com.example.covid_19eg.UI.Chart.ChartsActivity;
import com.example.covid_19eg.UI.DetailsActivity;
import com.example.covid_19eg.UI.InteractiveMapActivity;
import com.example.covid_19eg.UI.main.Menu.DrawerAdapter;
import com.example.covid_19eg.UI.main.Menu.DrawerItem;
import com.example.covid_19eg.UI.main.Menu.SimpleItem;
import com.example.covid_19eg.UI.main.Menu.SpaceItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener, DrawerAdapter.OnItemSelectedListener{

    private SlidingRootNav slidingRootNav;
    private static final int POS_DASHBOARD = 0;
    private static final int POS_ACCOUNT = 1;
    private static final int POS_MESSAGES = 2;
    private static final int POS_CART = 3;
    private static final int POS_LOGOUT = 5;

    private String[] screenTitles;
    private Drawable[] screenIcons;
    BottomNavigationView navView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navView = findViewById(R.id.nav_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //NAVIGATION


        slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(true)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.menu_left_drawer)
                .inject();

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        DrawerAdapter drawadapter = new DrawerAdapter(Arrays.asList(
                createItemFor(POS_DASHBOARD).setChecked(true),
                createItemFor(POS_ACCOUNT),
                createItemFor(POS_MESSAGES),
                createItemFor(POS_CART),
                new SpaceItem(48),
                createItemFor(POS_LOGOUT)));
        drawadapter.setListener(this);

        RecyclerView list = findViewById(R.id.list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(drawadapter);

        drawadapter.setSelected(POS_DASHBOARD);


    }

    @Override
    public void onItemSelected(int position) {

        if (position == POS_DASHBOARD) {

            navView = findViewById(R.id.nav_view);
            AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.navigation_home, R.id.navigation_world, R.id.navigation_contact)
                    .build();
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            NavigationUI.setupWithNavController(navView, navController);

        }

        if (position == POS_LOGOUT) {
            finish();
        }
        if (position == POS_ACCOUNT) {
               Intent i =new Intent(getApplication(), ChartsActivity.class);
              startActivity(i);
        }

        if (position == POS_CART

        ) {
               Intent i =new Intent(getApplication(), InteractiveMapActivity.class);
                 startActivity(i);
        }

        if (position == POS_MESSAGES

        ) {

            Intent i =new Intent(getApplication(), DetailsActivity.class);
            startActivity(i);
        }

        slidingRootNav.closeMenu();
        // Fragment selectedScreen = CenteredTextFragment.createFor(screenTitles[position]);
        // showFragment(selectedScreen);
    }



    private DrawerItem createItemFor(int position) {
        return new SimpleItem(screenIcons[position], screenTitles[position])
                .withIconTint(color(R.color.textColorSecondary))
                .withTextTint(color(R.color.textColorSecondary))
                .withSelectedIconTint(color(R.color.navfun))
                .withSelectedTextTint(color(R.color.navfun));
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.ld_activityScreenTitles);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.ld_activityScreenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }
    @ColorInt
    private int color(@ColorRes int res) {
        return ContextCompat.getColor(this, res);
    }
    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

}
