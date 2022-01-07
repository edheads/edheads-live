package com.example.demo.models.enums;

public enum StudentLunchPct {
    UNDER30("0-30"),
    FROM30_50("30-50"),
    FROM51_65("51-65"),
    FROM66_80("66-80"),
    ABOVE80("80+");


    private final String stringVersion;

    StudentLunchPct(String stringVersion) {
        this.stringVersion = stringVersion;
    }

    public String toString() {
        return this.stringVersion;
    }
}
