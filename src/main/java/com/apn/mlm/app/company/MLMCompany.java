package com.apn.mlm.app.company;

import com.apn.mlm.app.MLMNode;

public interface MLMCompany {
    boolean isIncludedInGroupBV(MLMNode downliner);
    long getEarnings(MLMNode node);
}
