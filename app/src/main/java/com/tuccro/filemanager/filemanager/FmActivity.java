package com.tuccro.filemanager.filemanager;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tuccro.filemanager.R;

import java.io.File;
import java.util.ArrayList;

public class FmActivity extends AppCompatActivity implements FilesFragment.OnFragmentInteractionListener {

    FragmentManager fragmentManager;
    FilesFragment filesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fm);

        fragmentManager = getSupportFragmentManager();
        init();
    }

    public void init() {

        if (filesFragment == null) filesFragment = new FilesFragment();

        fragmentManager.beginTransaction().replace(R.id.llFiles, filesFragment).commit();
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
                AddFolderDialog dialog = new AddFolderDialog(this, filesFragment.getCurrentDir());
                dialog.show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class AddFolderDialog extends AlertDialog {

        Context context;
        File path;
        EditText etName;
        ArrayList<File> dirsInDir;

        protected AddFolderDialog(Context context, final File path) {
            super(context);

            this.context = context;
            this.path = path;

            dirsInDir = new ArrayList<>();
            for (File file : path.listFiles()) {
                if (file.isDirectory()) dirsInDir.add(file);
            }

            this.setTitle("Create new folder");

            etName = new EditText(context);

            etName.setText("New Folder");
            etName.selectAll();
            this.setView(etName);

            this.setButton(BUTTON_POSITIVE, "Create", onClickListener);
            this.setButton(BUTTON_NEGATIVE, "Cancel", onClickListener);

            this.setOnShowListener(new OnShowListener() {
                @Override
                public void onShow(DialogInterface dialog) {
                    Button b = getButton(AlertDialog.BUTTON_POSITIVE);

                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            File file = new File(path, etName.getText().toString());

                            if (dirsInDir.contains(file)) {

                            } else {
                                if (file.mkdir()) {
                                    init();
                                    dismiss();
                                }
                            }
                        }
                    });
                }
            });
        }

        AlertDialog.OnClickListener onClickListener = new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        };
    }
}
