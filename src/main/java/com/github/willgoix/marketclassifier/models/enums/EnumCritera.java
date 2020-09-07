package com.github.willgoix.marketclassifier.models.enums;

/**
 * @author Willian Gois (github/willgoix) - 02/09/2020
 */
public enum EnumCritera {

    PRECOS("Preços"),
    ATENDIMENTO("Atendimento"),
    FILAS("Filas"),
    HIGIENE("Higiene"),
    ESTACIONAMENTO("Estacionamento"),
    LOCALIZACAO("Localização");

    private final String displayName;

    EnumCritera(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
