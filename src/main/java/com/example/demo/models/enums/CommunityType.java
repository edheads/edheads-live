package com.example.demo.models.enums;

public enum CommunityType {
    URBAN("urban"),
    SUBURBAN("suburban"),
    RURAL("rural"),
    CITY("city");


    CommunityType(String stringVersion) {
        this.stringVersion = stringVersion;
    }

    private final String stringVersion;

    public String toString() {
        return this.stringVersion;
    }


}
