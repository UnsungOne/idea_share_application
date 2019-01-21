package com.idea.share.com.idea.share.sorting;

public enum SortEnum {
    ADDED("Najnowsze wpisy"),
    SCORE("Najlepszy wynik");

    private final String showValue;

    SortEnum(String showValue) {
        this.showValue = showValue;
    }

    public String getValues() {
        return showValue;
    }
}