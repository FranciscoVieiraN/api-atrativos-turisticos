package com.atrativos.back.dto;

import com.atrativos.back.domain.City;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityDTO implements Serializable {

    private static final long SerialVersionID = 1L;

    private Long id;
    private String name;
    private String state;
    private String description;

    public static CityDTO toDTO(City city){
        return CityDTO
                .builder()
                .id(city.getId())
                .name(city.getName())
                .state(city.getState())
                .description(city.getDescription())
                .build();
    }
}
