package com.apn.mlm.app.company;

import com.apn.mlm.app.MLMNode;

public class AMWAY implements MLMCompany{

    final float UPLINE_BONUS = .06f;
    final float RETAIL_MARGINS = .35f;
    final float CUSTOMER_BV_PERCENTAGE = 1f/3;

    int pvScale[] = new int[] {0, 100, 300, 600, 1000, 1500, 2500, 4000, 6000, 7500, 10000, 12500, 15000};
    double bonusPercentages[] = new double[] {.0, .03, .06, .09, .12, .15, .18, .21, .23, .25, .27, .29, .31};

    @Override
    public boolean isIncludedInGroupBV(MLMNode downliner) {
        return downliner.getGroupPV() <= 7500;
    }

    @Override
    public long getEarnings(MLMNode node) {
        long earnings = 0;
        earnings += getBonusMoney(node.getGroupPV(), node.getGroupBV());

        for(MLMNode downliner: node.getDownliners()) {
            if(isIncludedInGroupBV(downliner)) {
                earnings -= getBonusMoney(downliner.getGroupPV(), downliner.getGroupBV());
            }else {
                earnings += UPLINE_BONUS * downliner.getGroupPV()*3.06;
            }
        }

        earnings += node.getBv() * RETAIL_MARGINS * CUSTOMER_BV_PERCENTAGE;

        return earnings;
    }

    public double getBonusMoney(long pv, long bv) {
        for(int i = 1; i < pvScale.length; i++) {
            if(pv >= pvScale[i-1] && pv < pvScale[i]) {
                return bv * bonusPercentages[i-1];
            }
        }

        return bv * bonusPercentages[bonusPercentages.length - 1];
    }

}
