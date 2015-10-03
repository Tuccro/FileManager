package com.tuccro.filemanager.filemanager;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.tuccro.filemanager.R;

import java.io.File;

public class FmActivity extends AppCompatActivity implements FilesFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fm);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.llFiles, new FilesFragment()).commit();
    }

    @Override
    public void onFragmentInteraction(File file) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.fm_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add_folder:
                Snackbar.make(getCurrentFocus(), "Add folder", Snackbar.LENGTH_SHORT)
                        .show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
