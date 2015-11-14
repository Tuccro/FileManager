package com.tuccro.filemanager.ui;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        checkStorage();

        intentRequest = getIntent().getIntExtra(KEY_REQUEST, GET_FILE);

        fragmentManager = getSupportFragmentManager();
        init();
    }

    /**
     * Finish activity if external storage not available for rw
     */
    public void checkStorage() {

        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            Toast.makeText(FileManagerActivity.this, R.string.sd_error, Toast.LENGTH_LONG).show();

            Intent intent = new Intent();
            setResult(Activity.RESULT_CANCELED, intent);
            finish();
        }
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
            findViewById(R.id.bt_ok).setVisibility(View.GONE);
        }

        if (filesFragment == null) filesFragment = new FilesFragment();

        fragmentManager.beginTransaction().replace(R.id.llFiles, filesFragment).commit();
    }

    /**
     * Return result
     *
     * @param file selected file or folder
     */
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

        AddFolderDialog dialog = new AddFolderDialog(this, filesFragment.getCurrentDir());
        dialog.show();
        return true;
    }

    /**
     * AlertDialog for new folder creating
     */
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

            this.setTitle(getString(R.string.create_folder));

            etName = new EditText(context);

            etName.setText(R.string.new_folder);
            etName.selectAll();
            this.setView(etName);

            this.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.create), onClickListener);
            this.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.cancel), onClickListener);

            this.setOnShowListener(new OnShowListener() {
                @Override
                public void onShow(DialogInterface dialog) {
                    Button b = getButton(AlertDialog.BUTTON_POSITIVE);

                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            File file = new File(path, etName.getText().toString());

                            if (!dirsInDir.contains(file)) {
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
