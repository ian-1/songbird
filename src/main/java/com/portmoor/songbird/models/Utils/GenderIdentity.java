package com.portmoor.songbird.models.Utils;

public enum GenderIdentity {
    TRANS("transgender"),
    CIS("cisgender"),
    NOTSTATED("not stated");

    private final String label;

    GenderIdentity(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
