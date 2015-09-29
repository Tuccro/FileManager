package com.tuccro.filemanager.filemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tuccro.filemanager.R;

import java.io.File;

/**
 * Created by Valentin on 9/29/2015.
 */
public class FilesAdapter extends BaseAdapter {

    File[] files;
    Context context;
    LayoutInflater layoutInflater;

    public FilesAdapter(File[] files, Context context) {
        this.files = files;
        this.context = context;
        layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return files.length;
    }

    @Override
    public File getItem(int position) {
        return files[position];
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
            ivIcon.setImageResource(R.drawable.ic_insert_drive_file_black_18dp);
        }

        tvName.setText(file.getName());

        return view;
    }
}
