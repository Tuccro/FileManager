package com.tuccro.filemanager.filemanager;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tuccro.filemanager.R;
import com.tuccro.filemanager.filemanager.utils.FilesSorter;

import java.io.File;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FilesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FilesFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    File root;
    File currentDir;

    ListView lvFiles;

    public FilesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_files, container, false);

        lvFiles = (ListView) view.findViewById(R.id.lvFiles);

        root = Environment.getExternalStorageDirectory();
        currentDir = root;

        init(currentDir);

        lvFiles.setOnItemClickListener(onItemClickListener);
        return view;
    }

    private void init(File dir) {

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
            if (file.exists() && file.isDirectory()) {

                init(file);
            }
        }
    };

    // TODO: Rename method, update argument and hook method into UI event

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

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
        public void onFragmentInteraction(Uri uri);
    }

}
