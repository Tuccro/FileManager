package com.tuccro.filemanager.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tuccro.filemanager.R;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Valentin on 9/29/2015.
 */
public class FilesAdapter extends BaseAdapter {

    ArrayList<File> files;
    Context context;
    LayoutInflater layoutInflater;
    boolean isRoot;

    public FilesAdapter(ArrayList<File> files, boolean isRoot, Context context) {
        this.files = files;
        this.isRoot = isRoot;
        this.context = context;
        layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return files.size();
    }

    @Override
    public File getItem(int position) {
        return files.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.file_item, parent, false);
        }

        File file = getItem(position);

        ImageView ivIcon = (ImageView) view.findViewById(R.id.iv_icon);
        TextView tvName = (TextView) view.findViewById(R.id.tv_file_name);

        if (file.isDirectory()) {

            ivIcon.setImageResource(R.drawable.ic_folder);
        } else {
            ivIcon.setImageResource(R.drawable.ic_file);
        }

        tvName.setText(file.getName());

        if (!isRoot && position == 0) {
            tvName.setText("");
            ivIcon.setImageResource(R.drawable.ic_keyboard_arrow_up_black_36dp);
        }

        return view;
    }
}
