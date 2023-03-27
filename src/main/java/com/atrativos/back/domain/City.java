package com.atrativos.back.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.atrativos.back.dto.CityDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class City implements Serializable {

    private static final long SerialVersionID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "The city name cannot be empty")
    @Column(length = 100, nullable = false)
    private String name;

    @NotEmpty(message = "The city state cannot be empty")
    @Column(length = 2, nullable = false)
    private String state;

    @NotEmpty(message = "The city description cannot be empty")
    @Column(length = 200, nullable = false)
    private String description;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attractive> attractions = new ArrayList<>();

    public static City toEntity(CityDTO cityDTO) {
        return City
                .builder()
                .id(cityDTO.getId())
                .name(cityDTO.getName())
                .state(cityDTO.getState())
                .description(cityDTO.getDescription())
                .build();
    }
}
