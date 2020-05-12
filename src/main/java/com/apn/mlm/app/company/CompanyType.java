package com.apn.mlm.app.company;

public enum CompanyType {

    amway;

    public static CompanyType getCompanyTypeFromString(String companyType){
        switch(companyType){
            case "amway":
                return amway;
            default:
                return amway;
        }
    }
}
