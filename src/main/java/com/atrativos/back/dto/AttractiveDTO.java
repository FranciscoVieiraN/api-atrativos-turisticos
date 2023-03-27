package com.atrativos.back.dto;

import com.atrativos.back.domain.Attractive;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttractiveDTO implements Serializable {

    private static final long SerialVersionID = 1L;

    private Long id;
    private String name;
    private String address;
    private String description;
    private String category;
    private Long cityID;

    public static AttractiveDTO toDTO(Attractive attractive){
        return AttractiveDTO
                .builder()
                .id(attractive.getId())
                .name(attractive.getName())
                .address(attractive.getAddress())
                .description(attractive.getDescription())
                .category(String.valueOf(attractive.category))
                .cityID(attractive.getCity().getId())
                .build();
    }
}
