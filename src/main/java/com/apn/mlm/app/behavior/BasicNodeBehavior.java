package com.apn.mlm.app.behavior;

import com.apn.mlm.app.MLMNode;

public class BasicNodeBehavior implements NodeBehavior {

    final static int MIN_TIME_ALIVE = 5;
    final static int MIN_EARNING = 100;
    final static int MAX_DOWNLINERS = 6;
    final static int MIN_SEARCHABLE_POP_SIZE = 300;
    final static double PERCENT_SUCCESS = .3;

    @Override
    public boolean isDropped(MLMNode node) {
        if (node.getTimeAlive() > MIN_TIME_ALIVE) {
            return node.getEarnings() < MIN_EARNING;
        }
        return false;
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public int getNumOfMonthlyDownliners(MLMNode node, int populationSize) {
        int n = Math.min(MIN_SEARCHABLE_POP_SIZE, populationSize);

        if (n == 0 || node.getDownliners().size() >= MAX_DOWNLINERS) {
            return 0;
        }

        return Math.random() < PERCENT_SUCCESS ? 1 : 0;
    }

}
