package com.atrativos.back.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.atrativos.back.dto.AttractiveDTO;
import com.atrativos.back.enums.AttractiveCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Attractive implements Serializable {

    private static final long SerialVersionID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "The attractive name cannot be empty")
    @Column(length = 100, nullable = false)
    private String name;

    @NotEmpty(message = "The attractive address cannot be empty")
    @Column(length = 100, nullable = false)
    private String address;

    @NotEmpty(message = "The attractive description cannot be empty")
    @Column(length = 200, nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public AttractiveCategory category;

    @ManyToOne
    @JoinColumn(name = "city_id")
    @JsonIgnoreProperties({"attractions"})
    private City city;

    public static Attractive toEntity(AttractiveDTO attractiveDTO, City city){
        return Attractive
                .builder()
                .id(attractiveDTO.getId())
                .name(attractiveDTO.getName())
                .address(attractiveDTO.getAddress())
                .description(attractiveDTO.getDescription())
                .category(AttractiveCategory.valueOf(attractiveDTO.getCategory().toUpperCase()))
                .city(city)
                .build();
    }
}
