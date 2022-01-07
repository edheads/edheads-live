package com.example.demo.models.enums;

public enum AccountType {
    INDIVIDUAL("individual"),
    COMPANY_REP("company representative");

    private final String stringVersion;

    AccountType(String stringVersion) {
        this.stringVersion = stringVersion;
    }

    public String toString() {
        return this.stringVersion;
    }
}
