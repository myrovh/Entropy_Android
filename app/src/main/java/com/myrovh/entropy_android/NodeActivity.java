package com.myrovh.entropy_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.mikepenz.materialize.MaterializeBuilder;
import com.myrovh.entropy_android.models.Node;

import org.parceler.Parcels;

public class NodeActivity extends AppCompatActivity {
    public static final String NODE_OBJECT_EXTRA = "node";
    private Node currentNode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_node);

        //Setup Materialize Theme
        new MaterializeBuilder().withActivity(this).build();

        //Get node data
        currentNode = Parcels.unwrap(getIntent().getParcelableExtra(NODE_OBJECT_EXTRA));

        //Setup Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(currentNode.getTitle());

        //Setup Views
        Toast.makeText(this, currentNode.getTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
