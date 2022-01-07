package com.example.demo.models.enums;

public enum GradeRange {
    K_3("K-3"),
    four_6("4-6"),
    seven_8("7-8"),
    nine_12("9-12"),
    twelvePlus("12+"),
    all("all");

    private final String stringVersion;

    GradeRange(String stringVersion) {
        this.stringVersion = stringVersion;
    }

    public String toString() {
        return this.stringVersion;
    }

}
