package com.tuccro.filemanager.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tuccro.filemanager.R;
import com.tuccro.filemanager.utils.FilesSorter;

import java.io.File;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FilesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FilesFragment extends Fragment {

    public static final String ARG_FILE = "arg_file";

    private OnFragmentInteractionListener mListener;

    private File root;

    private File currentDir;

    ListView lvFiles;

    public FilesFragment() {
    }

    public File getCurrentDir() {
        return currentDir;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        root = Environment.getExternalStorageDirectory();

        if (getArguments() != null && getArguments().containsKey(ARG_FILE)) {
            currentDir = new File(getArguments().getString(ARG_FILE));
        } else {
            currentDir = root;
        }

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_files, container, false);

        lvFiles = (ListView) view.findViewById(R.id.lvFiles);

        init(currentDir);

        lvFiles.setOnItemClickListener(onItemClickListener);
        return view;
    }

    public void init(File dir) {

        File[] dirFiles = dir.listFiles();
        FilesSorter sorter = new FilesSorter(dirFiles);

        ArrayList<File> sortedFilesList = sorter.getSortedListOfFiles();

        if (!dir.equals(root)) {
            sortedFilesList.add(0, dir.getParentFile());
        }

        this.currentDir = dir;

        boolean isRoot = dir.equals(root) ? true : false;

        FilesAdapter adapter = new FilesAdapter(sortedFilesList, isRoot, getActivity());

        lvFiles.setAdapter(adapter);
    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            File file = (File) parent.getAdapter().getItem(position);
            if (file.exists()) {

                if (file.isDirectory()) init(file);
                if (file.isFile() && mListener != null) mListener.onFragmentInteraction(file);
            }
        }
    };

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(File file);
    }

}
