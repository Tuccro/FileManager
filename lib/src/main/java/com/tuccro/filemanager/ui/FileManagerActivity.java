package com.tuccro.filemanager.ui;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

public class FileManagerActivity extends AppCompatActivity implements FilesFragment.OnFragmentInteractionListener {

    public static final String KEY_REQUEST = "request";
    public static String KEY_RESULT = "result";

    public static final int GET_FILE = 1;
    public static final int GET_FOLDER = 2;

    FragmentManager fragmentManager;
    FilesFragment filesFragment;

    Button btCancel;
    Button btOk;

    // default request is file getting
    int intentRequest = GET_FILE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fm);

        intentRequest = getIntent().getIntExtra(KEY_REQUEST, GET_FILE);

        fragmentManager = getSupportFragmentManager();
        init();
    }

    public void init() {

        btOk = (Button) findViewById(R.id.bt_ok);
        btCancel = (Button) findViewById(R.id.bt_cancel);

        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                returnFile(filesFragment.getCurrentDir());
            }
        });
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        if (intentRequest == GET_FILE) {
            findViewById(R.id.bt_ok).setEnabled(false);
        }

        if (filesFragment == null) filesFragment = new FilesFragment();

        fragmentManager.beginTransaction().replace(R.id.llFiles, filesFragment).commit();
    }

    private void returnFile(File file) {

        String path = file.getAbsolutePath();
        Intent intent = new Intent();
        intent.putExtra(KEY_RESULT, path);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public void onFragmentInteraction(File file) {
        if (intentRequest == GET_FILE) returnFile(file);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if (intentRequest == GET_FOLDER) {
            // Inflate the menu items for use in the action bar
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.fm_activity_menu, menu);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

//        switch (item.getItemId()) {
//            case R.id.action_add_folder:
        AddFolderDialog dialog = new AddFolderDialog(this, filesFragment.getCurrentDir());
        dialog.show();
        return true;
//        }

//        return super.onOptionsItemSelected(item);
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

            this.setButton(DialogInterface.BUTTON_POSITIVE, "Create", onClickListener);
            this.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", onClickListener);

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
                                    filesFragment.init(path);
                                    dismiss();
                                }
                            }
                        }
                    });
                }
            });
        }

        // Required empty OnClickListener
        AlertDialog.OnClickListener onClickListener = new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        };
    }
}
