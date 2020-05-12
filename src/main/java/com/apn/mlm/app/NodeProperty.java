package com.apn.mlm.app;

public enum NodeProperty {
    PV, BV, MONTHLY_INCOME;

    public static NodeProperty getPropertyFromString(String property) {
        switch(property) {
            case "PV":
                return PV;
            case "BV":
                return BV;
            case "MONTHLY_INCOME":
                return MONTHLY_INCOME;
            default:
                return MONTHLY_INCOME;
        }
    }
}
