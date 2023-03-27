package com.atrativos.back.util;

import com.atrativos.back.domain.Attractive;
import com.atrativos.back.domain.City;
import com.atrativos.back.enums.AttractiveCategory;

public class AttractiveCreator {

    public static Attractive createAttractiveToBeSaved(){
        City createCityToBeSaved = CityCreator.createCityToBeSaved();
        return Attractive.builder()
                .name("Praça da Vitoria")
                .address("Rio Tinto, PB, 58297-000")
                .description("Praça de Rio Tinto")
                .category(AttractiveCategory.CULTURA)
                .city(createCityToBeSaved)
                .build();
    }

    public static Attractive createValidAttractive(){
        City createCityToBeSaved = CityCreator.createCityToBeSaved();
        return Attractive.builder()
                .id(1L)
                .name("Praça da Vitoria")
                .address("Rio Tinto, PB, 58297-000")
                .description("Praça de Rio Tinto")
                .category(AttractiveCategory.CULTURA)
                .city(createCityToBeSaved)
                .build();
    }

    public static Attractive createValidUpdateAttractive(){
        City createCityToBeSaved = CityCreator.createCityToBeSaved();
        return Attractive.builder()
                .id(1L)
                .name("Praça da Vitoria2")
                .address("Rio Tinto, PB, 58297-000")
                .description("Praça de Rio Tinto")
                .category(AttractiveCategory.CULTURA)
                .city(createCityToBeSaved)
                .build();
    }
}
