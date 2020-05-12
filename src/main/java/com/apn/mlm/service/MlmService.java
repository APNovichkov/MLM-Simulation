package com.apn.mlm.service;

import java.util.List;

import com.apn.mlm.app.MLMEngine;
import com.apn.mlm.app.NodeProperty;
import com.apn.mlm.app.tracker.IBOTimelineTracker;
import com.apn.mlm.app.tracker.Tracker;
import com.apn.mlm.dto.IBOTimelineTrackerDTO;
import com.apn.mlm.dto.LevelBasedTrackerDTO;
import com.apn.mlm.dto.RunParamsDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class MlmService {

    MLMEngine engine = null;

    public void runModel(){
        engine = new MLMEngine();

        // set engine params
        engine.setMlmCompany(RunParamsDTO.company);
        engine.setNodeBehavior(RunParamsDTO.nodeBehavior);
        engine.setNodePopulation(RunParamsDTO.nodePopulation);
        engine.setTracker(RunParamsDTO.tracker);

        // run engine
        engine.runModel(RunParamsDTO.simulationLifespan);
    }

    public IBOTimelineTrackerDTO getIBOTimelineData(){
        Tracker tracker = engine.getTracker();
        return tracker instanceof IBOTimelineTracker ? buildIBOTimelineTrackerDTO((IBOTimelineTracker) tracker) : null;
    }

    private IBOTimelineTrackerDTO buildIBOTimelineTrackerDTO(IBOTimelineTracker tracker) {
        IBOTimelineTrackerDTO dto = new IBOTimelineTrackerDTO();

        List<List<Long>> timeline = tracker.getTimeline();


        dto.timeline = tracker.getTimeline();
        dto.id = 5;
        return dto;
    }

    public LevelBasedTrackerDTO getLevelBasedData(){
        return null;
    }

}
