package com.myrovh.entropy_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.mikepenz.fastadapter.adapters.FastItemAdapter;
import com.mikepenz.materialize.MaterializeBuilder;
import com.myrovh.entropy_android.models.Node;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Node> documentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setup Materialize Theme
        new MaterializeBuilder().withActivity(this).build();

        //Setup Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Setup Document List
        documentList.add(new Node("Test Node", "A test node with some text", "", "TEXT", new ArrayList<String>(), new ArrayList<Node>()));

        //TODO recycler view for nodes

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.node_document_view);
        FastItemAdapter fastAdapter = new FastItemAdapter();
        recyclerView.setAdapter(fastAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        NodeChildAdapter test = new NodeChildAdapter();
        test.title = "Title";
        test.tags = "Tag1, Tag2";
        fastAdapter.add(test);
        //TODO open node view on click
    }
}
