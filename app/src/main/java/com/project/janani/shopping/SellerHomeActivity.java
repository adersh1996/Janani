package com.project.janani.shopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class SellerHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public NavigationView navigationView;
    public BottomNavigationView bottomNavigationView;
    SellerHomeFragment sellerHomeFragment = new SellerHomeFragment();
    SellerOrderFragment sellerOrderFragment = new SellerOrderFragment();
    SellerHistoryFragment sellerHistoryFragment = new SellerHistoryFragment();
    SllerAccountFragment sellarAccountFragment = new SllerAccountFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_home);
        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, sellerHomeFragment).commit();

        }
        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    // override the onOptionsItemSelected()
    // function to implement
    // the item click listener callback
    // to open and close the navigation
    // drawer when the icon is clicked
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);


    }

    public void closeDrawer() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.sell_home_item:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, sellerHomeFragment).commit();
                closeDrawer();
                return true;

            case R.id.sell_order_item:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, sellerOrderFragment).commit();
                closeDrawer();
                return true;

            case R.id.sell_history_item:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, sellerHistoryFragment).commit();
                closeDrawer();
                return true;

            case R.id.sell_account_item:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, sellarAccountFragment).commit();
                closeDrawer();
                return true;
        }
        return false;
    }
}