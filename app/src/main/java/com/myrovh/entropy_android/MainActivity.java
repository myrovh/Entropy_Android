package com.myrovh.entropy_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mikepenz.aboutlibraries.Libs;
import com.mikepenz.aboutlibraries.LibsBuilder;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.adapters.FastItemAdapter;
import com.mikepenz.iconics.context.IconicsLayoutInflater;
import com.mikepenz.materialize.MaterializeBuilder;
import com.myrovh.entropy_android.models.Node;

import org.parceler.Parcels;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Node> documentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setup Materialize Theme
        new MaterializeBuilder().withActivity(this).build();

        //Setup Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Setup Document List
        int i = 0;
        while (i < 50) {
            ArrayList<String> tags = new ArrayList<>();
            tags.add("Tag " + i);
            tags.add("Tag " + i * 8);
            Node tempN = new Node("Test Node " + i, "A test node with some text", "", Node.NODE_TEXT_TYPE, tags, new ArrayList<Node>());
            documentList.add(tempN);
            i++;
            for (Node node : documentList) {
                ArrayList<String> subtags = new ArrayList<>();
                subtags.add("Sub Tag");
                node.addChild(new Node("Test Node " + i, "A test node with some text", "", Node.NODE_TEXT_TYPE, subtags, new ArrayList<Node>()));
            }
        }

        //Setup recycler view for nodes
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.node_document_view);
        FastItemAdapter fastAdapter = new FastItemAdapter();
        recyclerView.setAdapter(fastAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fastAdapter.add(Node.CreateNodeItemList(documentList));

        //Open node on click
        fastAdapter.withOnClickListener(new FastAdapter.OnClickListener<NodeChildItem>() {
            @Override
            public boolean onClick(View v, IAdapter<NodeChildItem> adapter, NodeChildItem item, int position) {
                OpenNode(position);
                return false;
            }
        });
    }

    private void OpenNode(int position) {
        Intent i = new Intent(MainActivity.this, NodeActivity.class);
        i.putExtra(NodeActivity.NODE_OBJECT_EXTRA, Parcels.wrap(documentList.get(position)));
        i.putExtra(Node.NODE_LEVEL_EXTRA, 0);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_about:
                new LibsBuilder().withActivityStyle(Libs.ActivityStyle.LIGHT_DARK_TOOLBAR).start(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
