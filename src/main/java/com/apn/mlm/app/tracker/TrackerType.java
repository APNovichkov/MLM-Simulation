package com.apn.mlm.app.tracker;

public enum TrackerType {
    iboTimeline, bizOverviewTimeline, levelBased;

    public static TrackerType getTrackerFromString(String trackerType) {
        switch(trackerType) {
            case "iboTimeline":
                return iboTimeline;
            case "bizOverviewTimeline":
                return bizOverviewTimeline;
            case "levelBased":
                return levelBased;
            default:
                return iboTimeline;
        }
    }
}
