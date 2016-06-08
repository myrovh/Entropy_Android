package com.myrovh.entropy_android;

import android.content.Context;
import android.util.Log;

import com.myrovh.entropy_android.models.Node;

import java.io.File;
import java.util.ArrayList;

class DocumentManager {
    public static final String FILE_EXTENSION = ".node";
    //TODO Document manager to load and save json files from storage location defined in settings
    //  Feed new node into json into new file in internal storage
    //  Scan internal storage and read root node to make adapter list of documents
    //  Selecting an item will cause the json file to be passed into an actual node object and then loaded into an activity

    //TODO Document manager contains handler to find and provide a list of ints allowing a node to update or create nodes

    DocumentManager() {
    }

    ArrayList<String> getFileList(Context context) {
        ArrayList<String> filenames = new ArrayList<>();

        File filesDir = context.getFilesDir();
        Log.d("Files", "Path: " + filesDir.toString());
        File[] files = filesDir.listFiles();

        for (File file : files) {
            if (file.toString().endsWith(FILE_EXTENSION)) {
                filenames.add(file.getName());
            }
        }
        return filenames;
    }

    ArrayList<Node> getSavedDocuments() {
        ArrayList<Node> documents = new ArrayList<>();

        return documents;
    }
}
