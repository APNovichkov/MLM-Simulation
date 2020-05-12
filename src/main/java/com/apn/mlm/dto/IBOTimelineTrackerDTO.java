package com.apn.mlm.dto;

import com.apn.mlm.app.tracker.IBOTimelineTracker;
import java.util.List;

public class IBOTimelineTrackerDTO {

    // Output data:

    /*

    {
        months: [
            1,
            2,
            3,
            4,
            5,
            6,
            ..
        ],
        nodes: [
            {
                nodeId: 1,
                values: [64, 78, ..]
            },
            {
                nodeId: 2,
                values: [75, 144, 254, ..]
            }
        ]
       }


     */


    // data
    public List<Integer> months;
    public List<NodeObject> nodes;

    public static class NodeObject{
        public int nodeId;
        public List<Long> values;

        public NodeObject(int nodeId) {
            this.nodeId = nodeId;
        }
    }

}
