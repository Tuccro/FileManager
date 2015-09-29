package com.tuccro.filemanager.filemanager;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.tuccro.filemanager.R;

public class FmActivity extends AppCompatActivity implements FilesFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fm);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.llFiles, new FilesFragment()).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
