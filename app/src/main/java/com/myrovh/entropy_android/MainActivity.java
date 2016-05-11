package com.myrovh.entropy_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
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
        fastAdapter.add(CreateTestData());

        //TODO open node view on click
        fastAdapter.withOnClickListener(new FastAdapter.OnClickListener<NodeChildAdapter>() {
            @Override
            public boolean onClick(View v, IAdapter<NodeChildAdapter> adapter, NodeChildAdapter item, int position) {
                Toast.makeText(v.getContext(), (item).title, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    private ArrayList<NodeChildAdapter> CreateTestData() {
        //TODO generate actual nodes with children then build function to generate list for adapter to use
        ArrayList<NodeChildAdapter> list = new ArrayList<>();
        int i = 0;
        while (i < 50) {
            NodeChildAdapter test = new NodeChildAdapter();
            test.title = "Title " + i;
            test.tags = "Tag1, Tag2";
            list.add(test);
            i++;
        }
        return list;
    }
}
