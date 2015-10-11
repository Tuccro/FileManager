package com.tuccro.filemanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.tuccro.filemanager.ui.FileManagerActivity;

/**
 * Created by tuccro on 10/11/15.
 */
public class FileManager {

    public static final int REQUEST_FILE = 5421;
    public static final int REQUEST_FOLDER = 5422;

    private static Intent intent;

    public static void getFile(Activity activity) {
        intent = new Intent();
        intent.putExtra(FileManagerActivity.KEY_REQUEST, FileManagerActivity.GET_FILE);
        start(activity, REQUEST_FILE);
    }

    public static void getFolder(Activity activity) {
        intent = new Intent();
        intent.putExtra(FileManagerActivity.KEY_REQUEST, FileManagerActivity.GET_FOLDER);
        start(activity, REQUEST_FOLDER);
    }

    private static Intent getIntent(Context context) {
        intent.setClass(context, FileManagerActivity.class);
        return intent;
    }

    private static void start(Activity activity, int requestCode) {
        activity.startActivityForResult(getIntent(activity), requestCode);
    }

}
