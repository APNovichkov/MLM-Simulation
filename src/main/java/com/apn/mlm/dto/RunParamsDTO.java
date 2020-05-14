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

    public int simulationLifespan = 100;
    public int startMonth = 20;
    public int endMonth = 50;
    public int numNodesToTrack = 10;

    // Population
    public int defaultPopulationSize = 10000000;
    public int defaultMonthlyPopulationIncrease = 0;

}
