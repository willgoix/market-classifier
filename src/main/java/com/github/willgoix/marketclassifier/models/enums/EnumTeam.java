package com.github.willgoix.marketclassifier.models.enums;

/**
 * @author Willian Gois (github/willgoix) - 02/09/2020
 */
public enum EnumTeam {

    TEAM_JAVA("Time de Java"),
    TEAM_DESIGN("Time de Design"),
    TEAM_SQL("Time de SQL"),
    TEAM_DATA("Time de Dados");

    private final String displayName;

    EnumTeam(String displayName) {
        this.displayName = displayName;
    }

    public int getId() {
        return this.ordinal();
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
