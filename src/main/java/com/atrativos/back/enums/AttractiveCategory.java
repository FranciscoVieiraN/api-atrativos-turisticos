package com.atrativos.back.enums;

public enum AttractiveCategory {

    AVENTURA("Aventura"),
    CULTURA("Cultura"),
    ECOTURISMO("Ecoturismo"),
    GASTRONOMIA("Gastronomia"),
    HISTORIA("História"),
    PRAIAS("Praias");

    public final String label;

    AttractiveCategory(String label) {
        this.label = label;
    }

    public String getName() {
        return label;
    }
}
