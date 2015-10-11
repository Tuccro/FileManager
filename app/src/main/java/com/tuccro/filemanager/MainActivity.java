package com.tuccro.filemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tuccro.filemanager.filemanager.FileManager;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_FILE = 1111;
    public static final int REQUEST_FOLDER = 1112;

    TextView tvFile;
    TextView tvFolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btSelectFile = (Button) findViewById(R.id.bt_select_file);
        Button btSelectFolder = (Button) findViewById(R.id.bt_select_folder);

        tvFile = (TextView) findViewById(R.id.tv_file_name);
        tvFolder = (TextView) findViewById(R.id.tv_folder_name);

        btSelectFile.setOnClickListener(onClick);
        btSelectFolder.setOnClickListener(onClick);
    }

    View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent;

            switch (v.getId()) {
                case R.id.bt_select_file:

                    intent = new Intent(MainActivity.this, FileManager.class);
                    startActivityForResult(intent, 1111);
                    break;
                case R.id.bt_select_folder:

                    intent = new Intent(MainActivity.this, FileManager.class);
                    intent.putExtra(FileManager.KEY_REQUEST, FileManager.GET_FOLDER);
                    startActivityForResult(intent, 1112);
                    break;
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            String result = "";

            switch (requestCode) {
                case REQUEST_FILE:

                    result = data.getStringExtra(FileManager.KEY_RESULT);
                    tvFile.setText(result);
                    break;
                case REQUEST_FOLDER:

                    result = data.getStringExtra(FileManager.KEY_RESULT);
                    tvFolder.setText(result);
                    break;
            }

        }
    }
}
