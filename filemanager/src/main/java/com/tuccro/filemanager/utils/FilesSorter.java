package com.tuccro.filemanager.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Valentin on 9/30/2015.
 */
public class FilesSorter {

//    ArrayList<File> content;

    ArrayList<File> folders;
    ArrayList<File> files;

    ArrayList<File> sortedListOfFiles;

    public FilesSorter(File[] content) {
        this.sortedListOfFiles = new ArrayList<>(Arrays.asList(content));

        folders = new ArrayList<>();
        files = new ArrayList<>();
    }

    public ArrayList<File> getSortedListOfFiles() {
        sortByName();
        sortFoldersFirst();

        return sortedListOfFiles;
    }

    private void sortByName() {
        Collections.sort(sortedListOfFiles, new Comparator<File>() {
            @Override
            public int compare(File lhs, File rhs) {
                return lhs.getName().compareTo(rhs.getName());
            }
        });
    }

    private void sortFoldersFirst() {
        for (File file : sortedListOfFiles) {
            if (file.isDirectory()) folders.add(file);
            else files.add(file);
        }

        sortedListOfFiles = new ArrayList<>(folders);
        sortedListOfFiles.addAll(files);
    }
}
