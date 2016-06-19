package com.myrovh.entropy_android;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.myrovh.entropy_android.models.Node;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

class DocumentManager {
    static final String FILE_EXTENSION = ".node";
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    //TODO Document manager to load and save json files from storage location defined in settings
    //  Feed new node into json into new file in internal storage
    //  Scan internal storage and read root node to make adapter list of documents
    //  Selecting an item will cause the json file to be passed into an actual node object and then loaded into an activity

    //TODO Document manager contains handler to find and provide a list of ints allowing a node to update or create nodes

    DocumentManager() {
    }

    //Returns array of strings with the filenames (not absolute path) to all valid saved documents
    ArrayList<String> getFileList(Context context) {
        ArrayList<String> filenames = new ArrayList<>();

        File filesDir = context.getFilesDir();
        File[] files = filesDir.listFiles();

        for (File file : files) {
            if (file.toString().endsWith(FILE_EXTENSION)) {
                filenames.add(file.getName());
            }
        }
        return filenames;
    }

    //Load every file in internal memory with the correct FILE_EXTENSION into an arraylist
    ArrayList<Node> getSavedDocuments(Context context) {
        ArrayList<Node> documents = new ArrayList<>();
        ArrayList<String> filenames = getFileList(context);

        for (String filename : filenames) {
            Node temp;
            try {
                FileInputStream in = context.openFileInput(filename);
                JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
                temp = gson.fromJson(reader, Node.class);
                documents.add(temp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return documents;
    }
}
