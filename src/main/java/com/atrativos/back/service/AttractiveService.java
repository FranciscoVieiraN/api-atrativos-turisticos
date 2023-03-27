package com.atrativos.back.service;

import com.atrativos.back.domain.Attractive;
import com.atrativos.back.domain.City;
import com.atrativos.back.repository.AttractiveRepository;
import com.atrativos.back.dto.AttractiveDTO;
import com.atrativos.back.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttractiveService {

    private final AttractiveRepository attractiveRepository;
    private final CityService cityService;

    public Page<Attractive> listAll(Pageable pageable) {
        return attractiveRepository.findAll(pageable);
    }

    public List<Attractive> listAllNonPageable() {
        return attractiveRepository.findAll();
    }

    public Attractive findByIdOrThrowBadRequestException(Long id) {
        return  attractiveRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Attractive not Found"));
    }

    public List<Attractive> findByName(String name) {
        return attractiveRepository.findByName(name);
    }

    public List<Attractive> findByAddress(String address) {
        return attractiveRepository.findByAddress(address);
    }

    public List<Attractive> findAllAttractivesByCity(Long cityID){
        return attractiveRepository.findAllAttracivesByCity(cityID);
    }

    @Transactional
    public Attractive save(AttractiveDTO attractiveDTO) {
        City cityOBJ = cityService.findByIdOrThrowBadRequestException(attractiveDTO.getCityID());

        return attractiveRepository.save(Attractive.toEntity(attractiveDTO, cityOBJ));
    }

    public void delete(Long id) {
        attractiveRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public AttractiveDTO replace(Long id, AttractiveDTO newObj) {
        Attractive oldObj = findByIdOrThrowBadRequestException(id);
        updateData(oldObj, newObj);
        return AttractiveDTO.toDTO(attractiveRepository.save(oldObj));
    }

    private void updateData(Attractive oldObj, AttractiveDTO newObj) {
        oldObj.setName(newObj.getName());
        oldObj.setAddress(newObj.getAddress());
        oldObj.setDescription(newObj.getDescription());
        City cityOBJ = cityService.findByIdOrThrowBadRequestException(newObj.getCityID());
        oldObj.setCity(cityOBJ);
    }

}
