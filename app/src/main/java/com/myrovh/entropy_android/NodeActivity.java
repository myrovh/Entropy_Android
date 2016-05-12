package com.myrovh.entropy_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.mikepenz.aboutlibraries.Libs;
import com.mikepenz.aboutlibraries.LibsBuilder;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.adapters.FastItemAdapter;
import com.mikepenz.materialize.MaterializeBuilder;
import com.myrovh.entropy_android.models.Node;

import org.parceler.Parcels;

public class NodeActivity extends AppCompatActivity {
    public static final String NODE_OBJECT_EXTRA = "node";
    private Node currentNode;
    private int nodeLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_node);

        //Setup Materialize Theme
        new MaterializeBuilder().withActivity(this).build();

        //Get node data
        currentNode = Parcels.unwrap(getIntent().getParcelableExtra(NODE_OBJECT_EXTRA));
        nodeLevel = getIntent().getIntExtra(Node.NODE_LEVEL_EXTRA, -1);
        if (nodeLevel >= 0) {
            nodeLevel++;
        }

        //Setup Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(currentNode.getTitle() + " Level " + nodeLevel);

        //Setup Views
        TextView tagText = (TextView) findViewById(R.id.node_tags_text);
        TextView descriptionText = (TextView) findViewById(R.id.node_description_text);

        tagText.setText(Node.CreateNodeTagString(currentNode.getTags()));
        descriptionText.setText(currentNode.getDescription());

        //Setup recycler view for children nodes
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.node_children_view);
        FastItemAdapter fastAdapter = new FastItemAdapter();
        recyclerView.setAdapter(fastAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fastAdapter.add(Node.CreateNodeItemList(currentNode.getChildren()));
        //TODO show 'no children' empty list when adapter is empty

        //Open node on click
        fastAdapter.withOnClickListener(new FastAdapter.OnClickListener<NodeChildItem>() {
            @Override
            public boolean onClick(View v, IAdapter<NodeChildItem> adapter, NodeChildItem item, int position) {
                OpenChildNode(position);
                return false;
            }
        });
    }

    private void OpenChildNode(int position) {
        Intent i = new Intent(NodeActivity.this, NodeActivity.class);
        i.putExtra(NodeActivity.NODE_OBJECT_EXTRA, Parcels.wrap(currentNode.getChild(position)));
        i.putExtra(Node.NODE_LEVEL_EXTRA, nodeLevel);
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
                new LibsBuilder().withFields(R.string.class.getFields()).withActivityStyle(Libs.ActivityStyle.LIGHT_DARK_TOOLBAR).start(this);
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void AddChildNode(View view) {
    }
}
