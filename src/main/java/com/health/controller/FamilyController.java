package com.health.controller;

import com.health.dto.FamilyDTO;
import com.health.dto.MedicDTO;
import com.health.model.Family;
import com.health.model.Medic;
import com.health.service.IFamilyService;
import com.health.service.IMedicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/families")
@RequiredArgsConstructor
public class FamilyController {
    private final IFamilyService service;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<FamilyDTO>> findAll() throws Exception{
        List<FamilyDTO> list = service.findAll().stream().map(this::convertToDto).toList(); // e -> convertToDto(e)
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FamilyDTO>  findById(@PathVariable("id") Integer id) throws Exception{
        FamilyDTO obj = convertToDto(service.findById(id)) ;
        return ResponseEntity.ok(obj);
    }

    @PostMapping
    public ResponseEntity<FamilyDTO> save(@Valid @RequestBody FamilyDTO dto) throws Exception{
        Family obj = service.save(convertToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdFamily()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<FamilyDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody FamilyDTO dto) throws Exception{
        Family entity = convertToEntity(dto);
        entity.setIdFamily(id);
        Family obj =  service.update(entity, id);
        return ResponseEntity.ok(convertToDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Convertir de un Modelo a un DTO
    private FamilyDTO convertToDto(Family obj){
        return modelMapper.map(obj, FamilyDTO.class);
    }

    // Convertir de un DTO a un Modelo (Entity)
    private Family convertToEntity(FamilyDTO dto){
        return modelMapper.map(dto, Family.class);
    }
}
