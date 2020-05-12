package com.apn.mlm.app.tracker;

import com.apn.mlm.app.MLMNode;
import com.apn.mlm.app.NodeProperty;

import java.util.ArrayList;
import java.util.List;

public class IBOTimelineTracker implements Tracker{

    private int startMonth;
    private int numberOfNodesToTrack;
    private NodeProperty propertyToTrack;
    private NodeProperty property;
    private List<Integer> time = new ArrayList<>();
    private List<List<Long>> timeline = new ArrayList<>();
    private List<MLMNode> nodesToTrack = new ArrayList<>();
    private List<String> header = new ArrayList<>();

//    private Path tsvOutputPath = Paths.get("/Users/apnovichkov/Desktop/MLMOutputs/iboTL.txt");


    public IBOTimelineTracker(int startMonth, int numberOfNodesToTrack, NodeProperty property) {
        this.startMonth = startMonth;
        this.numberOfNodesToTrack = numberOfNodesToTrack;
        this.property = property;
    }

    public int getStartMonth() {
        return startMonth;
    }

    public NodeProperty getPropertyToTrack() {
        return propertyToTrack;
    }

    public NodeProperty getProperty() {
        return property;
    }

    @Override
    public void doMonth(MLMNode node, int month) {
        System.out.println("Doing month #" + month + " for iboTimeline");
        if(month == startMonth) {
            System.out.println("Selecting nodes");
            selectTrackingNodes();
        }

        if(month > startMonth) {
            addTimePoint(month);
        }
    }

    private void selectTrackingNodes() {
        List<MLMNode> tmpNodes = new ArrayList<>();
        for(int i = 0; i < numberOfNodesToTrack; i++) {
            if(nodesToTrack.size() == 0) {
                break;
            }
            int nodeIndex = (int)( Math.random()*nodesToTrack.size() );
            tmpNodes.add(nodesToTrack.get(nodeIndex));
            nodesToTrack.remove(nodeIndex);

            header.add("IBO Node #" + i);
        }
        nodesToTrack = tmpNodes;
    }


    private void addTimePoint(int month) {
        this.time.add(month);
        List<Long> nodesProperties = new ArrayList<>();
        for(MLMNode node: nodesToTrack) {
            if(node.isDropped()) {
                nodesProperties.add(0l);
            } else {
                nodesProperties.add(node.getPropertyValue(property));
            }

        }
        timeline.add(nodesProperties);
    }

    @Override
    public void save2TSV() {
//        Save2TSV.save(timeline, header, tsvOutputPath);
    }

    @Override
    public String toJSON() {
        //        JsonObjectBuilder builder = Json.createObjectBuilder();
//        JsonArrayBuilder months = Json.createArrayBuilder();

        //Add months
//        for(Integer month: time) {
//            months.add(month);
//        }


        //Add Months and Nodes and their Values
//        JsonArrayBuilder nodes = Json.createArrayBuilder();
//        for(int i = 0; i < nodesToTrack.size(); i++) {
//            JsonObjectBuilder node = Json.createObjectBuilder();
//            JsonArrayBuilder properties = Json.createArrayBuilder();
//            MLMNode currentNode = nodesToTrack.get(i);
//
//
//            for(List<Long> monthProps: timeline) {
//                properties.add(monthProps.get(i));
//            }
//
//            node.add("nodeId",currentNode.getId());
//            node.add("values", properties);
//            nodes.add(node);
//        }
//
//        builder.add("months", months);
//        builder.add("nodes", nodes);
//
//
//        return builder.build().toString();
        return "{hello: hi}";
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=================\n");
        sb.append("IBO Timeline stat\n");
        sb.append("=================\n");
        sb.append("Tracking property: " + property + "\n");
        sb.append("Start month: " + startMonth + "\n");
        sb.append("MAX numberOfNodesToTrack: " + numberOfNodesToTrack + "\n");
        sb.append("\n");

        for(int i = 0; i < time.size(); i++) {
            int month = time.get(i);
            List<Long> nodesValues = timeline.get(i);
            sb.append("Values[" + month + "] = " );
            for(Long value: nodesValues) {
                sb.append(value + " \t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public void nodeAdded(MLMNode node, int month) {
        if(month == startMonth) {
            nodesToTrack.add(node);
            System.out.println("Added node to nodeToTrack");
        }
    }



    public List<List<Long>> getTimeline() {
        return timeline;
    }

    public List<MLMNode> getNodesToTrack() {
        return nodesToTrack;
    }

    public List<Integer> getTime() {
        return time;
    }
}
