package com.apn.mlm.app.behavior;

import com.apn.mlm.app.MLMNode;

public class DefaultNodeBehavior implements NodeBehavior {

    @Override
    public boolean isDropped(MLMNode node) {
        return false;
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public int getNumOfMonthlyDownliners(MLMNode node, int populationSize) {
        if(populationSize == 0) return 0;
        return Math.random() < .5 ? 1 : 0;
    }
}
