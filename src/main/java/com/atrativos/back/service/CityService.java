package com.atrativos.back.service;

import com.atrativos.back.domain.City;
import com.atrativos.back.dto.CityDTO;
import com.atrativos.back.repository.CityRepository;
import com.atrativos.back.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    public Page<City> listAllPageable(Pageable pageable) {
        return cityRepository.findAll(pageable);
    }

    public List<City> listAll() {
        return cityRepository.findAll();
    }

    public List<City> findByName(String name) {
        return cityRepository.findByName(name);
    }

    public List<City> findAllCitiesByState(String state) {
        return cityRepository.findAllCitiesByState(state);
    }

    public City findByIdOrThrowBadRequestException(Long id) {
        Optional<City> obj = cityRepository.findById(id);
        return obj.orElseThrow(() -> new BadRequestException("City not Found"));
    }

    @Transactional
    public CityDTO save(CityDTO cityDTO) throws BadRequestException{
        Optional<City> existCity = cityRepository.existCity(cityDTO.getName(), cityDTO.getState());

        City city = City.toEntity(cityDTO);

        if(existCity.isPresent()){
            throw  new BadRequestException("City already exists");
        }

        cityRepository.save(city);
        return CityDTO.toDTO(city);
    }

    public void delete(Long id) {
        cityRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public CityDTO replace(Long id, CityDTO cityDTO) throws BadRequestException{
        City city = findByIdOrThrowBadRequestException(id);

        Optional<City> existCity = cityRepository.existCity(cityDTO.getName(), cityDTO.getState());

        if((existCity.isPresent()) && (existCity.get().getId() != id)){
            throw  new BadRequestException("City already exists");
        }

        city.setName(cityDTO.getName());
        city.setState(cityDTO.getState());
        city.setDescription(cityDTO.getDescription());

       return CityDTO.toDTO(cityRepository.save(city));
    }
    
}
