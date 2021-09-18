package ru.digitalleague.factory.ok.enums;

public enum Language {
    RUSSIAN("ru"),
    ENGLISH("en");

    private final String localeCode;

    Language(String localeCode) {
        this.localeCode = localeCode;
    }

    public String getLocaleCode() {
        return localeCode;
    }
}
