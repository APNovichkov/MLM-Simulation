package com.apn.mlm.app;

import com.apn.mlm.app.behavior.BasicNodeBehavior;
import com.apn.mlm.app.behavior.NodeBehavior;
import com.apn.mlm.app.company.AMWAY;
import com.apn.mlm.app.company.MLMCompany;
import com.apn.mlm.app.population.DefaultNodePopulation;
import com.apn.mlm.app.population.NodePopulation;
import com.apn.mlm.app.tracker.IBOTimelineTracker;
import com.apn.mlm.app.tracker.LevelBasedTracker;
import com.apn.mlm.app.tracker.Tracker;
import com.apn.mlm.app.tracker.TrackerType;

public class LocalLauncher {

    private final static int DEFAULT_SIMULATION_LIFESPAN = 36;
    private final static int DEFAULT_START_MONTH = 6;
    private final static int DEFAULT_END_MONTH = 20;
    private final static int DEFAULT_NUM_NODES_TO_TRACK = 5;
    private final static Tracker DEFAULT_TRACKER = new IBOTimelineTracker(DEFAULT_START_MONTH, DEFAULT_NUM_NODES_TO_TRACK, NodeProperty.MONTHLY_INCOME);
    private final static MLMCompany DEFAULT_MLM_COMPANY = new AMWAY();

    private MLMCompany getMLMCompany(CompanyType companyType) {
        switch(companyType) {
            case amway:
                return new AMWAY();
            default:
                return new AMWAY();
        }
    }

    public static void main(String[] args) {
        //Defaults for now
        NodeProperty property = NodeProperty.MONTHLY_INCOME;
        Tracker tracker = DEFAULT_TRACKER;
        MLMCompany company = DEFAULT_MLM_COMPANY;


        // Build Engine params
        MLMCompany mlmCompany = company;
        NodeBehavior nodeBehavior = new BasicNodeBehavior();
        NodePopulation nodePopulation = new DefaultNodePopulation();

        // Build MLM Engine
        MLMEngine engine = new MLMEngine();
        engine.setMlmCompany(mlmCompany);
        engine.setNodeBehavior(nodeBehavior);
        engine.setNodePopulation(nodePopulation);
        engine.setTracker(tracker);

        // Run Engine
        engine.runModel(DEFAULT_SIMULATION_LIFESPAN);

        System.out.println(tracker.toString());
    }
}
