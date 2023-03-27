package com.atrativos.back.repository;

import com.atrativos.back.domain.Attractive;
import com.atrativos.back.util.AttractiveCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Test for Attractive Repository")
class AttractiveRepositoryTest {

    @Autowired
    private AttractiveRepository attractiveRepository;

    @Test
    @DisplayName("Save persists attractive when Successful")
    void save_PersistAttractive_WhenSuccessful(){
        Attractive createAttractiveToBeSaved = AttractiveCreator.createAttractiveToBeSaved();

        Attractive attractiveSaved = this.attractiveRepository.save(createAttractiveToBeSaved);

        Assertions.assertThat(attractiveSaved).isNotNull();

        Assertions.assertThat(attractiveSaved.getId()).isNotNull();

        Assertions.assertThat(attractiveSaved.getName()).isEqualTo(createAttractiveToBeSaved.getName());
    }

    @Test
    @DisplayName("Save updates attractive when Successful")
    void save_UpdatesAttractive_WhenSuccessful(){
        Attractive createAttractiveToBeSaved = AttractiveCreator.createAttractiveToBeSaved();

        Attractive attractiveSaved = this.attractiveRepository.save(createAttractiveToBeSaved);

        attractiveSaved.setName("Igreja");

        Attractive attractiveUpdated = this.attractiveRepository.save(attractiveSaved);

        Assertions.assertThat(attractiveUpdated).isNotNull();

        Assertions.assertThat(attractiveUpdated.getId()).isNotNull();

        Assertions.assertThat(attractiveUpdated.getName()).isEqualTo(attractiveSaved.getName());
    }

    @Test
    @DisplayName("Delete removes attractive when Successful")
    void delete_RemovesCity_WhenSuccessful(){
        Attractive createAttractiveToBeSaved = AttractiveCreator.createAttractiveToBeSaved();

        Attractive attractiveSaved = this.attractiveRepository.save(createAttractiveToBeSaved);

        this.attractiveRepository.delete(attractiveSaved);

        Optional<Attractive> attractiveOptional = this.attractiveRepository.findById(attractiveSaved.getId());

        Assertions.assertThat(attractiveOptional).isEmpty();
    }

    @Test
    @DisplayName("Find By Name returns list of attractions when Successful")
    void findByName_ReturnsListOfCity_WhenSuccessful(){
        Attractive createAttractiveToBeSaved = AttractiveCreator.createAttractiveToBeSaved();

        Attractive attractiveSaved = this.attractiveRepository.save(createAttractiveToBeSaved);

        String name = attractiveSaved.getName();

        List<Attractive> attractions = this.attractiveRepository.findByName(name);

        Assertions.assertThat(attractions)
                .isNotEmpty()
                .contains(attractiveSaved);
    }

    @Test
    @DisplayName("Find By Address returns List of attractive when Successful")
    void findByAddress_ReturnsOptionalOfCity_WhenSuccessful(){
        Attractive createAttractiveToBeSaved = AttractiveCreator.createAttractiveToBeSaved();

        Attractive attractiveSaved = this.attractiveRepository.save(createAttractiveToBeSaved);

        String address = attractiveSaved.getAddress();

        List<Attractive> attractions = this.attractiveRepository.findByAddress(address);

        Assertions.assertThat(attractions)
                .isNotEmpty()
                .contains(attractiveSaved);
    }

    @Test
    @DisplayName("Find By Name returns empty list when no attractive is found")
    void findByName_ReturnsEmptyList_WhenAttractiveIsNotFound(){
        List<Attractive> cities = this.attractiveRepository.findByName("XUXA");

        Assertions.assertThat(cities).isEmpty();
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when name is empty")
    void save_ConstraintViolationException_WhenNameIsEmpty(){
        Attractive attractive = new Attractive();
//        Assertions.assertThatThrownBy(() -> this.attractiveRepository.save(attractive))
//                .isInstanceOf(ConstraintViolationException.class);

        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.attractiveRepository.save(attractive))
                .withMessageContaining("The attractive name cannot be empty");
    }
}
