package com.example.bloodlineapp.recipient;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.bloodlineapp.EventsActivity;
import com.example.bloodlineapp.MapsActivity;
import com.example.bloodlineapp.MyProfile;
import com.example.bloodlineapp.R;
import com.example.bloodlineapp.Selection;
import com.example.bloodlineapp.donor.ChangeProfile;
import com.example.bloodlineapp.donor.InformationActivity;
import com.google.android.material.navigation.NavigationView;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ImageView menu_post, menu_banks, menu_infos, menu_announce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        menu_post = (ImageView) findViewById(R.id.menu_post);
        menu_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, PostActivity.class);
                startActivity(intent);
            }
        });
        menu_infos = (ImageView) findViewById(R.id.menu_infos);
        menu_infos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, InformationActivity.class);
                startActivity(intent);
            }
        });
        menu_banks = (ImageView) findViewById(R.id.menu_banks);
        menu_banks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
        menu_announce = (ImageView) findViewById(R.id.menu_announce);
        menu_announce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, EventsActivity.class);
                startActivity(intent);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("BloodLine");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        TextView name = (TextView) headerView.findViewById(R.id.profilename);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_myProfile) {
            Intent intent = new Intent(DashboardActivity.this, MyProfile.class);
            startActivity(intent);
        }
        if (id == R.id.nav_changeProfile) {
            Intent intent = new Intent(DashboardActivity.this, ChangeProfile.class);
            startActivity(intent);

        }else if (id == R.id.menuLogout) {
            Intent intent = new Intent(DashboardActivity.this, Selection.class);
            Toast.makeText(DashboardActivity.this,"Logout successfully!",Toast.LENGTH_LONG).show();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
}