package com.apn.mlm.app;

import com.apn.mlm.app.behavior.NodeBehavior;
import com.apn.mlm.app.company.MLMCompany;
import com.apn.mlm.app.population.NodePopulation;

import java.util.ArrayList;
import java.util.List;

public class MLMNode {
    static final int STATE_OUT = 0;
    static final int STATE_IN = 1;
    static final int STATE_DROPPED = 2;

    static int NODE_ID_OFFSET = 0;

    int state;
    boolean active = false;

    //nTire
    List<MLMNode> downliners = new ArrayList<>();
    MLMNode upliner;
    MLMEngine engine;

    //globalParameters;
    long timeAlive;
    long totalSavings;
    long totalEarnings;
    long netEarnings;
    int teamSize;
    int depth = 1;
    int id;

    //monthlyParameters
    long pv;
    long bv;

    long groupPV;
    long groupBV;

    long earnings;
    long savings;


    public MLMNode(MLMEngine engine) {
        this.engine = engine;
        state = STATE_IN;
        id = ++NODE_ID_OFFSET;
    }

    public MLMNode(MLMEngine engine, MLMNode upliner) {
        this.engine = engine;
        this.upliner = upliner;
        state = STATE_IN;
    }

    //functions
    public void updateFinancialStatus() {
        // purchase product every month
        purchaseProduct();

        //updateParameters
        timeAlive ++;
        teamSize = 1;

        //doDownliners
        for(MLMNode downliner: downliners) {
            downliner.updateFinancialStatus();
            teamSize += downliner.teamSize;
        }

        //update personal PV, BV and depth of team
        updateGroupPV();
        updateGroupBV();
        updateDepth();

        //moneyyyy
        earnings = company().getEarnings(this);
        totalEarnings += earnings;
    }

    public void updateGrowth() {
        //traversal through the tree
        for(MLMNode downliner: downliners) {
            downliner.updateGrowth();
        }

        if(behavior().isActive()) {
            int nodesCount = behavior().getNumOfMonthlyDownliners(this, population().availableNodes());
            List<MLMNode> nodes = population().fetchNodes(nodesCount, engine);
            teamSize += nodes.size();
            addDownliners(nodes);
        }
    }

    public void updateDecline() {
        for(MLMNode downliner: downliners) {
            if(behavior().isDropped(downliner)) {
                downliner.state = STATE_DROPPED;
            }
            downliner.updateDecline();
        }


        //run backwards
        for(int i = downliners.size()-1; i >= 0; i--) {
            MLMNode downliner = downliners.get(i);
            if(downliner.state == STATE_DROPPED) {
                downliners.remove(i);
                population().nodeDropped(downliner);
                reconnectDownliners(downliner.downliners);
                teamSize--;
            }
        }
    }

    private void purchaseProduct() {
        if(upliner == null) {
            bv = 900;
            pv = 300;
        } else {
            bv = 450;
            pv = 150;
        }
    }

    private void reconnectDownliners(List<MLMNode> downlinersToReconnect) {
        for(MLMNode downliner: downlinersToReconnect) {
            downliner.state = STATE_IN;
            downliner.setUpline(this);
            downliners.add(downliner);
        }
    }

    private void addDownliners(List<MLMNode> downlinersToAdd) {
        for(MLMNode downliner: downlinersToAdd) {
            downliner.state = STATE_IN;
            downliner.setUpline(this);
            downliners.add(downliner);
            engine.nodeAdded(downliner);
        }
    }

    private void updateGroupBV() {
        groupBV = bv;
        if(downliners != null) {
            for(MLMNode downliner: downliners) {
                if(company().isIncludedInGroupBV(downliner)  ) {
                    groupBV += downliner.groupBV;
                }
            }
        }
    }

    private void updateGroupPV() {
        groupPV = pv;
        if(downliners != null) {
            for(MLMNode downliner: downliners) {
                groupPV += downliner.groupPV;
            }
        }
    }

    private void updateDepth() {
        depth=1;
        if(downliners != null) {
            int downlinerMaxDepth = 1;
            for(MLMNode downliner: downliners) {
                downlinerMaxDepth = Math.max(downlinerMaxDepth, downliner.depth);
            }
            depth+=downlinerMaxDepth;
        }
    }


    public int nodesWithMaxEarning(int maxEarning) {
        int count =  timeAlive == 0 ? 0 :  (earnings < maxEarning ? 1 : 0);
        for(MLMNode node: downliners) {
            count += node.nodesWithMaxEarning(maxEarning);
        }
        return count;
    }


    public String toString() {
        return "Month in Business: " + timeAlive + "\tTeam Size: " + teamSize +
                "\tGroup PV:" + groupPV + "\tGroup BV:" + groupBV +  " \tThis Month's Earnings: " + earnings;
    }

    public long getPropertyValue(NodeProperty property) {
        switch(property) {
            case PV:
                return getPv();
            case BV:
                return getBv();
            case MONTHLY_INCOME:
                return getEarnings();
            default:
                return 0;
        }
    }

    public void setUpline(MLMNode upliner) {
        this.upliner = upliner;
    }

    private MLMCompany company() {
        return engine.getMlmCompany();
    }

    private NodeBehavior behavior() {
        return engine.getNodeBehavior();
    }

    private NodePopulation population() {
        return engine.getNodePopulation();
    }

    public long getEarnings() {
        return earnings;
    }

    public int getDepth() {
        return depth;
    }

    public int getId() {
        return id;
    }

    public boolean isDropped() {
        return state == STATE_DROPPED;
    }

    public long getGroupPV() {
        return groupPV;
    }

    public long getGroupBV() {
        return groupBV;
    }

    public List<MLMNode> getDownliners() {
        return downliners;
    }

    public long getPv() {
        return pv;
    }

    public long getBv() {
        return bv;
    }

    public long getTimeAlive() {
        return timeAlive;
    }

    public int getTeamSize() {
        return teamSize;
    }
}
