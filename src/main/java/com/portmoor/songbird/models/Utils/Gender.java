package com.portmoor.songbird.models.Utils;

public enum Gender {
    MALE("male"),
    FEMALE("female"),
    NONBINARY("non-binary"),
    OTHER("other");

    private final String label;

    Gender(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
