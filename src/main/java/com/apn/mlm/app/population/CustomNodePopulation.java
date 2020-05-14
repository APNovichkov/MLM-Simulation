package com.apn.mlm.app.population;

import java.util.ArrayList;
import java.util.List;

import com.apn.mlm.app.MLMEngine;
import com.apn.mlm.app.MLMNode;

public class CustomNodePopulation implements NodePopulation{

    private int size;
    private int monthlyIncrease;

    private int nodesDroppedCount;
    private int nodesAvailableCount;

    public CustomNodePopulation(int size, int monthlyIncrease) {
        this.size = size;
        this.monthlyIncrease = monthlyIncrease;

        this.nodesAvailableCount = size;
        this.nodesDroppedCount = 0;
    }

    @Override
    public String getName() {
        return "Default";
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<MLMNode> fetchNodes(int nodeCount, MLMEngine engine) {
        List<MLMNode> nodes = new ArrayList<>();
        for(int i = 0; i < nodeCount; i++) {
            nodes.add(new MLMNode(engine));
            nodesAvailableCount--;
        }
        return nodes;
    }

    @Override
    public void monthlyUpdate() {
        size += monthlyIncrease;
        nodesAvailableCount += monthlyIncrease;
    }

    @Override
    public void nodeDropped(MLMNode node) {
        nodesDroppedCount++;
    }

    @Override
    public int availableNodes() {
        return nodesAvailableCount;
    }

    @Override
    public int droppedNodes() {
        return nodesDroppedCount;
    }

}
