package com.apn.mlm.app;

import com.apn.mlm.app.behavior.NodeBehavior;
import com.apn.mlm.app.company.MLMCompany;
import com.apn.mlm.app.population.NodePopulation;
import com.apn.mlm.app.tracker.Tracker;

public class MLMEngine implements NodeListener{

    private MLMCompany mlmCompany;
    private NodeBehavior nodeBehavior;
    private NodePopulation nodePopulation;
    private Tracker tracker;
    private MLMNode root;

    private int month;

    public MLMEngine() {
        root = new MLMNode(this);
    }

    public void runModel(int numberOfMonths) {
        System.out.println("Running MLM model");
        for(month = 0; month < numberOfMonths; month++) {
            //DO MONTH FOR EVERYONE...Starting at the root!
            doMonth(month, root);

            //Update population every month
            nodePopulation.monthlyUpdate();
        }
    }


    private void doMonth(int monthNum, MLMNode root) {
        //Update financial status of everyone
        root.updateFinancialStatus();

        //Update growth of everyone
        root.updateGrowth();

        //Tell the tracker to also do the month
        tracker.doMonth(root, monthNum);

        //Update decline in growth for everyone
        root.updateDecline();
    }

    public MLMCompany getMlmCompany() {
        return mlmCompany;
    }
    public void setMlmCompany(MLMCompany mlmCompany) {
        this.mlmCompany = mlmCompany;
    }
    public NodeBehavior getNodeBehavior() {
        return nodeBehavior;
    }
    public void setNodeBehavior(NodeBehavior nodeBehavior) {
        this.nodeBehavior = nodeBehavior;
    }
    public NodePopulation getNodePopulation() {
        return nodePopulation;
    }
    public void setNodePopulation(NodePopulation nodePopulation) {
        this.nodePopulation = nodePopulation;
    }
    public void setTracker(Tracker tracker) {
        this.tracker = tracker;
    }
    public Tracker getTracker() {
        return tracker;
    }

    @Override
    public void nodeAdded(MLMNode node) {
        if(tracker != null) {
            tracker.nodeAdded(node, month);
        }
    }
}
