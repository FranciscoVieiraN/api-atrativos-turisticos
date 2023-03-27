package com.atrativos.back.util;

import com.atrativos.back.domain.City;

public class CityCreator {

    public static City createCityToBeSaved(){
        return City.builder()
                .name("Rio Tinto")
                .state("PB")
                .description("Cidade da industria de tercido")
                .build();
    }

    public static City createValidCity(){
        return City.builder()
                .id(1L)
                .name("Rio Tinto")
                .state("PB")
                .description("Cidade da industria de tercido")
                .build();
    }

    public static City createValidUpdateCity(){
        return City.builder()
                .id(1L)
                .name("Rio Tinto2")
                .state("PB")
                .description("Cidade da industria de tercido")
                .build();
    }
}
