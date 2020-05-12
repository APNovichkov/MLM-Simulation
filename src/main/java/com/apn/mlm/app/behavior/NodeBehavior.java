package com.apn.mlm.app.behavior;

import com.apn.mlm.app.MLMNode;

public interface NodeBehavior {

    public boolean isDropped(MLMNode node);
    public boolean isActive();
    public int getNumOfMonthlyDownliners(MLMNode node, int populationSize);

}
