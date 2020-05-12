package com.apn.mlm.app.stat;

import java.util.Collections;
import java.util.List;

public class NodesStat {

    long median;
    long min;
    long max;
    long top10;
    long bottom10;

    public NodesStat(List<Long> inputList) {
        calculateStats(inputList);
    }

    public void calculateStats(List<Long> inputList) {
        if (inputList.size() != 0) {
            Collections.sort(inputList);
            min = inputList.get(0);
            max = inputList.get(inputList.size() - 1);
            median = inputList.get(inputList.size() / 2);
            top10 = inputList.get((int) (inputList.size() * .90));
            bottom10 = inputList.get((int) (inputList.size() * .1));
        }
    }

    public String getPrintout() {
        StringBuilder sb = new StringBuilder();
        sb.append("Min: " + min + "\n");
        sb.append("Max: " + max + "\n");
        sb.append("Median: " + median + "\n");
        sb.append("Top10: " + top10 + "\n");
        sb.append("Bottom10: " + bottom10 + "\n");
        return sb.toString();
    }

}
