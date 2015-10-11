package com.tuccro.filemanager.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tuccro.filemanager.ui.FileManagerActivity;

public class MainActivity extends AppCompatActivity {

    Button btGetFile;
    Button btGetFolder;

    TextView tvFilePath;
    TextView tvFolderPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {

        btGetFile = (Button) findViewById(R.id.bt_get_file);
        btGetFolder = (Button) findViewById(R.id.bt_get_folder);

        tvFilePath = (TextView) findViewById(R.id.tv_file_path);
        tvFolderPath = (TextView) findViewById(R.id.tv_folder_path);

        btGetFile.setOnClickListener(onClickListener);
        btGetFolder.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(MainActivity.this, FileManagerActivity.class);

            switch (v.getId()) {
                case R.id.bt_get_file:

                    intent.putExtra(FileManagerActivity.KEY_REQUEST, FileManagerActivity.GET_FILE);
                    break;
                case R.id.bt_get_folder:

                    intent.putExtra(FileManagerActivity.KEY_REQUEST, FileManagerActivity.GET_FOLDER);
                    break;
            }

            startActivityForResult(intent, 1488);
        }
    };
}
