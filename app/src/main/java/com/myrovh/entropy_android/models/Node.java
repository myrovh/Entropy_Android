package com.myrovh.entropy_android.models;

import com.myrovh.entropy_android.NodeChildItem;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class Node {
    public static final String NODE_TEXT_TYPE = "TEXT";
    public static final String NODE_IMAGE_TYPE = "IMAGE";
    public static final String NODE_LEVEL_EXTRA = "level";

    String title;
    String description;
    String imageLocation;
    String nodeType;
    ArrayList<String> tags;
    ArrayList<Node> children;

    public Node() {
    }

    public Node(String title, String description, String imageLocation, String nodeType, ArrayList<String> tags, ArrayList<Node> children) {
        this.title = title;
        this.description = description;
        this.imageLocation = imageLocation;
        this.nodeType = nodeType;
        this.tags = tags;
        this.children = children;
    }

    public static ArrayList<NodeChildItem> CreateNodeItemList(ArrayList<Node> nodes) {
        //Generate recycler list from nodes
        ArrayList<NodeChildItem> list = new ArrayList<>();
        for (Node node : nodes) {
            String tags = CreateNodeTagString(node.getTags());
            list.add(new NodeChildItem(node.getTitle(), tags));
        }

        return list;
    }

    public static String CreateNodeTagString(ArrayList<String> tags) {
        String s = "";
        for (String string : tags) {
            s += string + ", ";
        }
        return s;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Node> children) {
        this.children = children;
    }

    public Node getChild(int i) {
        return children.get(i);
    }

    public void addChild(Node n) {
        children.add(n);
    }

    public void removeChild(int i) {
        children.remove(i);
    }

    public void addtag(String t) {
        tags.add(t);
    }

    public void removeTag(int i) {
        tags.remove(i);
    }
}
