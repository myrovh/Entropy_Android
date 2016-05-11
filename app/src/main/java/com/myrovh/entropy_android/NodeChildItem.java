package com.myrovh.entropy_android;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mikepenz.fastadapter.items.AbstractItem;

public class NodeChildItem extends AbstractItem<NodeChildItem, NodeChildItem.ViewHolder> {
    public String title;
    public String tags;

    public NodeChildItem(String title, String tags) {
        this.title = title;
        this.tags = tags;
    }

    @Override
    public int getType() {
        return R.id.fastadapter_nodechildadapter_id;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_child_node;
    }

    @Override
    public void bindView(ViewHolder viewHolder) {
        super.bindView(viewHolder);

        viewHolder.title.setText(title);
        viewHolder.tags.setText(tags);
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView title;
        protected TextView tags;

        public ViewHolder(View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.node_title_text);
            this.tags = (TextView) view.findViewById(R.id.node_tags_text);
        }
    }
}
