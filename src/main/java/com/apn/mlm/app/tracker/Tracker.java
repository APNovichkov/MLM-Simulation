package com.apn.mlm.app.tracker;

import com.apn.mlm.app.MLMNode;

public interface Tracker {
    public void save2TSV();
    public String toJSON();
    public void doMonth(MLMNode node, int month);

    //This is up to trackers to use this as they wish. Just lets them know we added a node
    public void nodeAdded(MLMNode node, int month);
}
