package com.example.ware_house_management_android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ware_house_management_android.contracts.auth.LogoutContract;
import com.example.ware_house_management_android.models.UserModel;
import com.example.ware_house_management_android.presenters.LogoutPresenter;
import com.example.ware_house_management_android.ui.input.CreateInputFragment;
import com.example.ware_house_management_android.utils.UserUtil;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ware_house_management_android.databinding.ActivityMainBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LogoutContract.View {

    private LogoutPresenter logoutPresenter;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(view ->
                Snackbar.make(view, "Create import request", Snackbar.LENGTH_LONG)
                        .setAction("Go to", v -> {

                        })
                        .setAnchorView(R.id.fab).show());
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_user, R.id.nav_create_input)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        UserModel currentUser = UserUtil.currentUser(this);
        Log.i("CurrentUser", currentUser.getRole());
        checkFragmentByRole(currentUser.getRole());
        View headerView = navigationView.getHeaderView(0);
        TextView titleTextView = headerView.findViewById(R.id.title_textView);
        titleTextView.setText(currentUser.getFullName());
        ImageView navImageView = headerView.findViewById(R.id.imageView);
        Picasso.get().load("https://cdn-icons-png.freepik.com/512/3361/3361571.png").into(navImageView);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        logoutPresenter = new LogoutPresenter(this, this);
        MenuItem logoutItem = menu.findItem(R.id.action_logout);
        logoutItem.setOnMenuItemClickListener(item -> {
            logoutPresenter.logout();
            return true;
        });

        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void showLogoutSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void showLogoutError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    private void checkFragmentByRole(String role) {
        Menu menu = binding.navView.getMenu();
        ArrayList<Integer> menuItems = new ArrayList<>();
        menuItems.add(R.id.nav_home);
        switch (role) {
            case "Admin":
                menuItems.add(R.id.nav_user);
                break;
            case "Report Staff":
                menuItems.add(R.id.nav_create_input);
                break;
        }
        for (int i = 0; i < menu.size(); i++) {
            MenuItem menuItem = menu.getItem(i);
            if (!menuItems.contains(menuItem.getItemId())) {
                menu.removeItem(menuItem.getItemId());
            }
        }
    }
}