package com.tuccro.filemanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.tuccro.filemanager.ui.FileManagerActivity;

import java.io.File;

/**
 * Created by tuccro on 10/11/15.
 */
public class FileManager {

    public static final int REQUEST_FILE = 5421;
    public static final int REQUEST_FOLDER = 5422;

    private static Intent intent;

    /**
     * Open file manager and select the file
     *
     * @param activity activity that sends the request and receives a response
     */
    public static void getFile(Activity activity) {
        intent = new Intent();
        intent.putExtra(FileManagerActivity.KEY_REQUEST, FileManagerActivity.GET_FILE);
        start(activity, REQUEST_FILE);
    }

    /**
     * Open file manager and select the folder
     *
     * @param activity activity that sends the request and receives a response
     */
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

    /**
     * Get path of selected folder or file
     *
     * @param result intent from onActivityResult
     * @return path
     */
    @Nullable
    public static String getPathFromResultIntent(Intent result) {

        return result.getStringExtra(FileManagerActivity.KEY_RESULT);
    }

    /**
     * Get File object of selected folder or file
     *
     * @param result intent from onActivityResult
     * @return File object
     */
    public static File getFileFromResultIntent(Intent result) {
        return new File(getPathFromResultIntent(result));
    }
}
