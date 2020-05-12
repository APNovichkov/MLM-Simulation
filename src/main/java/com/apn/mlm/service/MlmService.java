package com.apn.mlm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.apn.mlm.app.MLMEngine;
import com.apn.mlm.app.MLMNode;
import com.apn.mlm.app.NodeProperty;
import com.apn.mlm.app.behavior.BasicNodeBehavior;
import com.apn.mlm.app.behavior.NodeBehavior;
import com.apn.mlm.app.company.AMWAY;
import com.apn.mlm.app.company.MLMCompany;
import com.apn.mlm.app.population.DefaultNodePopulation;
import com.apn.mlm.app.population.NodePopulation;
import com.apn.mlm.app.tracker.IBOTimelineTracker;
import com.apn.mlm.app.tracker.Tracker;
import com.apn.mlm.dto.IBOTimelineTrackerDTO;
import com.apn.mlm.dto.LevelBasedTrackerDTO;
import com.apn.mlm.dto.RunParamsDTO;
import com.google.common.collect.Streams;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class MlmService {

    MLMEngine engine = null;

    public void runModel(RunParamsDTO runParams){

        Tracker tracker = new IBOTimelineTracker(runParams.startMonth, runParams.numNodesToTrack, NodeProperty.MONTHLY_INCOME);
        MLMCompany company = new AMWAY();

        NodeBehavior nodeBehavior = new BasicNodeBehavior();
        NodePopulation nodePopulation = new DefaultNodePopulation();

        engine = new MLMEngine();

        // set engine params
        engine.setMlmCompany(company);
        engine.setNodeBehavior(nodeBehavior);
        engine.setNodePopulation(nodePopulation);
        engine.setTracker(tracker);

        // run engine
        engine.runModel(runParams.simulationLifespan);
    }

    public IBOTimelineTrackerDTO getIBOTimelineData(){
        Tracker tracker = engine.getTracker();
        return tracker instanceof IBOTimelineTracker ? buildIBOTimelineTrackerDTO((IBOTimelineTracker) tracker) : null;
    }

    private IBOTimelineTrackerDTO buildIBOTimelineTrackerDTO(IBOTimelineTracker tracker) {
        IBOTimelineTrackerDTO dto = new IBOTimelineTrackerDTO();

        dto.months = tracker.getTime();
        dto.nodes = tracker.getNodesToTrack().stream()
            .map(node -> { return new IBOTimelineTrackerDTO.NodeObject(node.getId()); })
            .collect(Collectors.toList());

        for (int i = 0; i < dto.nodes.size(); i++) {
            final int nodeIndex = i;
            dto.nodes.get(i).values = tracker.getTimeline().stream()
                    .map(month -> { return month.get(nodeIndex);})
                    .collect(Collectors.toList());
        }

        return dto;
    }

    public LevelBasedTrackerDTO getLevelBasedData(){
        return null;
    }

}
