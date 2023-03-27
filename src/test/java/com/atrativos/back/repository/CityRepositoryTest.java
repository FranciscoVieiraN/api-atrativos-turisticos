package com.atrativos.back.repository;

import com.atrativos.back.domain.City;
import com.atrativos.back.util.CityCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Test for City Repository")
class CityRepositoryTest {

    @Autowired
    private CityRepository cityRepository;

    @Test
    @DisplayName("Save persists city when Successful")
    void save_PersistCity_WhenSuccessful(){
        City createCityToBeSaved = CityCreator.createCityToBeSaved();

        City citySaved = this.cityRepository.save(createCityToBeSaved);

        Assertions.assertThat(citySaved).isNotNull();

        Assertions.assertThat(citySaved.getId()).isNotNull();

        Assertions.assertThat(citySaved.getName()).isEqualTo(createCityToBeSaved.getName());
    }

    @Test
    @DisplayName("Save updates city when Successful")
    void save_UpdatesCity_WhenSuccessful(){
        City createCityToBeSaved = CityCreator.createCityToBeSaved();

        City citySaved = this.cityRepository.save(createCityToBeSaved);

        citySaved.setName("Guarabira");

        City cityUpdated = this.cityRepository.save(citySaved);

        Assertions.assertThat(cityUpdated).isNotNull();

        Assertions.assertThat(cityUpdated.getId()).isNotNull();

        Assertions.assertThat(cityUpdated.getName()).isEqualTo(citySaved.getName());
    }

    @Test
    @DisplayName("Delete removes city when Successful")
    void delete_RemovesCity_WhenSuccessful(){
        City createCityToBeSaved = CityCreator.createCityToBeSaved();

        City citySaved = this.cityRepository.save(createCityToBeSaved);

        this.cityRepository.delete(citySaved);

        Optional<City> cityOptional = this.cityRepository.findById(citySaved.getId());

        Assertions.assertThat(cityOptional).isEmpty();
    }

    @Test
    @DisplayName("Find By Name returns list of cities when Successful")
    void findByName_ReturnsListOfCity_WhenSuccessful(){
        City createCityToBeSaved = CityCreator.createCityToBeSaved();

        City citySaved = this.cityRepository.save(createCityToBeSaved);

        String name = citySaved.getName();

        List<City> cities = this.cityRepository.findByName(name);

        Assertions.assertThat(cities)
                .isNotEmpty()
                .contains(citySaved);

    }

    @Test
    @DisplayName("Find By Address returns Optional of city when Successful")
    void findByState_ReturnsListOfCity_WhenSuccessful(){
        City createCityToBeSaved = CityCreator.createCityToBeSaved();

        City citySaved = this.cityRepository.save(createCityToBeSaved);

        String state = citySaved.getState();

        List<City> cities = this.cityRepository.findAllCitiesByState(state);

        Assertions.assertThat(cities)
                .isNotEmpty()
                .contains(citySaved);
    }

    @Test
    @DisplayName("Find By Name returns empty list when no city is found")
    void findByName_ReturnsEmptyList_WhenCityIsNotFound(){
        List<City> cities = this.cityRepository.findByName("XUXA");

        Assertions.assertThat(cities).isEmpty();
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when name is empty")
    void save_ConstraintViolationException_WhenNameIsEmpty(){
        City city = new City();
//        Assertions.assertThatThrownBy(() -> this.cityRepository.save(city))
//                .isInstanceOf(ConstraintViolationException.class);

        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.cityRepository.save(city))
                .withMessageContaining("The city name cannot be empty");
    }
}
