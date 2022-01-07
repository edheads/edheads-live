package com.example.demo.models.enums;

public enum StudentCount {
    UNDER250("0-250"),
    FROM250_500("250-500"),
    FROM501_2000("501-2,000"),
    FROM2001_5000("2,001-5,000"),
    FROM5001_10000("5,001-10,000"),
    FROM10001_20000("10,001-20,000"),
    FROM20001_50000("20,001-50,000"),
    ABOVE50000("50,000+");

    private final String stringVersion;

    StudentCount(String stringVersion) {
        this.stringVersion = stringVersion;
    }

    public String toString() {
        return this.stringVersion;
    }


}
