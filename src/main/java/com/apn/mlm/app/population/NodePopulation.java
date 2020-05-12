package com.apn.mlm.app.population;

import com.apn.mlm.app.MLMEngine;
import com.apn.mlm.app.MLMNode;

import java.util.List;

public interface NodePopulation {
    public String getName();

    public int size();
    public int availableNodes();
    public int droppedNodes();

    public List<MLMNode> fetchNodes(int nodeCount, MLMEngine engine);
    public void monthlyUpdate();
    public void nodeDropped(MLMNode node);
}
