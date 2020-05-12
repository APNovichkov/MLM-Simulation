package com.apn.mlm.dto;

import com.apn.mlm.app.NodeProperty;
import com.apn.mlm.app.behavior.BasicNodeBehavior;
import com.apn.mlm.app.behavior.NodeBehavior;
import com.apn.mlm.app.company.AMWAY;
import com.apn.mlm.app.company.MLMCompany;
import com.apn.mlm.app.population.DefaultNodePopulation;
import com.apn.mlm.app.population.NodePopulation;
import com.apn.mlm.app.tracker.IBOTimelineTracker;
import com.apn.mlm.app.tracker.Tracker;

public class RunParamsDTO {

    public static int simulationLifespan = 36;
    public static int startMonth = 6;
    public static int endMonth = 36;
    public static int numNodesToTrack = 5;

    public static Tracker tracker = new IBOTimelineTracker(startMonth, numNodesToTrack, NodeProperty.MONTHLY_INCOME);
    public static MLMCompany company = new AMWAY();

    public static NodeBehavior nodeBehavior = new BasicNodeBehavior();
    public static NodePopulation nodePopulation = new DefaultNodePopulation();
}
