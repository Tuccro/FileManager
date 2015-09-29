package com.tuccro.filemanager.filemanager;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.tuccro.filemanager.R;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FilesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FilesFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

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
        init();

        return view;
    }

    private void init() {
        File file = Environment.getExternalStorageDirectory();

        if (file.exists() && file.isDirectory()) {

            File[] dirFiles = file.listFiles();

            Toast.makeText(getActivity(), String.valueOf(dirFiles.length), Toast.LENGTH_SHORT).show();

            String[] strings = new String[dirFiles.length];

            for (int k = 0; k < strings.length; k++) {
                strings[k] = dirFiles[k].getName();
            }

            FilesAdapter adapter = new FilesAdapter(dirFiles, getActivity());

            lvFiles.setAdapter(adapter);
        }
    }

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
