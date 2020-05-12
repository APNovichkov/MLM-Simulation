package com.apn.mlm.app;

public enum CompanyType {
    amway;

    public static CompanyType getCompanyFromString(String property) {
        switch(property) {
            case "AMWAY":
                return amway;
            default:
                return amway;
        }
    }
}
