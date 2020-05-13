package com.apn.mlm.api;


import com.apn.mlm.app.MLMEngine;
import com.apn.mlm.app.NodeProperty;
import com.apn.mlm.app.tracker.IBOTimelineTracker;
import com.apn.mlm.app.tracker.Tracker;
import com.apn.mlm.dto.IBOTimelineTrackerDTO;
import com.apn.mlm.dto.LevelBasedTrackerDTO;
import com.apn.mlm.dto.RunParamsDTO;
import com.apn.mlm.service.MlmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/mlm")
public class MlmController {

    @Autowired
    private MlmService mlmService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public void runModel(@RequestBody RunParamsDTO runParams){
        runParams = new RunParamsDTO();
        mlmService.runModel(runParams);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path="ibotimeline")
    public IBOTimelineTrackerDTO getIBOTimelineData(){
        return mlmService.getIBOTimelineData();
    }

    @GetMapping(path="/levelbased")
    public LevelBasedTrackerDTO getLevelBasedData(){
        return mlmService.getLevelBasedData();
    }
}
