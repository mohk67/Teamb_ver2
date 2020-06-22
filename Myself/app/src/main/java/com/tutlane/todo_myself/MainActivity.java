package com.tutlane.todo_myself;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout dl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar tb = findViewById(R.id.tb);
        setSupportActionBar(tb);

        dl = findViewById(R.id.dl);

        //add three-line sign on top left
        ActionBarDrawerToggle adt = new ActionBarDrawerToggle(this, dl, tb, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        dl.addDrawerListener(adt);
        adt.syncState();

    }

    @Override
    public void onBackPressed() {
        if (dl.isDrawerOpen(GravityCompat.START)) {
            dl.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
