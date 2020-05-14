package com.apn.mlm.app.population;

import com.apn.mlm.app.MLMEngine;
import com.apn.mlm.app.MLMNode;

import java.util.ArrayList;
import java.util.List;

public class DefaultNodePopulation implements NodePopulation{
    private int populationSize = 1000;
    private final int MONTHLY_GROWTH = 0;

    @Override
    public int size() {
        return populationSize;
    }

    @Override
    public List<MLMNode> fetchNodes(int nodeCount, MLMEngine engine) {
        List<MLMNode> outputList = new ArrayList<>();
        for(int i = 0; i < nodeCount; i++) {
            outputList.add(new MLMNode(engine));
        }
        return outputList;
    }

    @Override
    public void monthlyUpdate() {
        // Population grows by 10k every month
        populationSize += MONTHLY_GROWTH;
    }

    @Override
    public void nodeDropped(MLMNode node) { }

    @Override
    public String getName() {
        return "default";
    }

    @Override
    public int availableNodes() {
        return populationSize;
    }

    @Override
    public int droppedNodes() {
        // TODO Auto-generated method stub
        return 0;
    }
}
