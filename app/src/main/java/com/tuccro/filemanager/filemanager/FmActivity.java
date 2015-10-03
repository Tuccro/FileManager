package com.tuccro.filemanager.filemanager;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

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
                // TODO: 10/4/2015 get real folder path
                AddFolderDialog dialog = new AddFolderDialog(this, Environment.getExternalStorageDirectory());
                dialog.show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class AddFolderDialog extends AlertDialog {

        Context context;

        protected AddFolderDialog(Context context, File path) {
            super(context);

            this.context = context;
            this.setTitle("Create new folder");

            EditText etName = new EditText(context);

            etName.setText("New Folder");
            etName.selectAll();
            this.setView(etName);

            this.setButton(BUTTON_POSITIVE, "Create", onClickListener);
            this.setButton(BUTTON_NEGATIVE, "Cancel", onClickListener);
        }

        AlertDialog.OnClickListener onClickListener = new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case BUTTON_POSITIVE:
//                        Snackbar.make(getCurrentFocus(), "Add folder", Snackbar.LENGTH_SHORT)
//                                .show();
                        break;
                }
            }
        };
    }
}
