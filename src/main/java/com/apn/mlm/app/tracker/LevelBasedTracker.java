package com.apn.mlm.app.tracker;

import com.apn.mlm.app.MLMNode;
import com.apn.mlm.app.NodeProperty;
import com.apn.mlm.app.stat.NodesStat;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class LevelBasedTracker implements Tracker{
    Deque<MLMNode> que = new ArrayDeque<MLMNode>();
    List<Integer> time = new ArrayList<>();
    List<List<NodesStat>> monthlyLevelStats = new ArrayList<>();

    private int endMonth;
    private int startMonth;
    private NodeProperty propertyToTrack;

    public LevelBasedTracker(int startMonth, int endMonth, NodeProperty propertyToTrack) {
        this.startMonth = startMonth;
        this.endMonth = endMonth;
        this.propertyToTrack = propertyToTrack;
    }

    public void doMonth(MLMNode root, int month) {
        if(month >= startMonth && month <= endMonth) {
            time.add(month);
            que.add(root);
            iterateLevels(root);
        }
    }

    public void iterateLevels(MLMNode root) {
        List<NodesStat> levelStats = new ArrayList<>();
        int size = que.size();
        while(size > 0) {
            List<Long> levelProperties = new ArrayList<>();
            for(int i = 0; i < size; i++) {
                MLMNode currentNode = que.poll();

                for(MLMNode downliner: currentNode.getDownliners()) {
                    que.add(downliner);
                }

                levelProperties.add(currentNode.getPropertyValue(propertyToTrack));
            }
            levelStats.add(new NodesStat(levelProperties));
            size = que.size();
        }
        monthlyLevelStats.add(levelStats);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("======================\n");
        sb.append("Level Tracker Printout\n");
        sb.append("======================\n");
        sb.append("Tracking property: " + propertyToTrack + "\n");
        sb.append("Start month: " + startMonth + "\n");
        sb.append("End month: " + endMonth + "\n");
        sb.append("\n");

        for(int i = 0; i < time.size(); i++) {
            int month = time.get(i);
            sb.append("MONTH: " + month + "\n");
            sb.append("=========\n");
            List<NodesStat> levelStats = monthlyLevelStats.get(i);
            for(int level = 0; level < levelStats.size(); level++) {
                sb.append("Stats for level " + level + ":\n");
                sb.append(levelStats.get(level).getPrintout());
                sb.append("------------------\n");
            }
        }
        return sb.toString();
    }

    @Override
    public void save2TSV() {
        // TODO Auto-generated method stub

    }

    @Override
    public String toJSON() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void nodeAdded(MLMNode node, int month) {
        // TODO Auto-generated method stub

    }
}
