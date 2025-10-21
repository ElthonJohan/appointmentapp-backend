package com.health.controller;

import com.health.dto.LaboratoryDTO;
import com.health.model.Laboratory;
import com.health.service.ILaboratoryService;
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
@RequestMapping("/laboratories")
@RequiredArgsConstructor
public class LaboratoryController {
    private final ILaboratoryService service;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<LaboratoryDTO>> findAll() throws Exception{
        List<LaboratoryDTO> list = service.findAll().stream().map(this::convertToDto).toList(); // e -> convertToDto(e)
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LaboratoryDTO>  findById(@PathVariable("id") Integer id) throws Exception{
        LaboratoryDTO obj = convertToDto(service.findById(id)) ;
        return ResponseEntity.ok(obj);
    }

    @PostMapping
    public ResponseEntity<LaboratoryDTO> save(@Valid @RequestBody LaboratoryDTO dto) throws Exception{
        Laboratory obj = service.save(convertToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdLaboratory()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<LaboratoryDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody LaboratoryDTO dto) throws Exception{
        Laboratory entity = convertToEntity(dto);
        entity.setIdLaboratory(id);
        Laboratory obj =  service.update(entity, id);
        return ResponseEntity.ok(convertToDto(obj));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Convertir de un Modelo a un DTO
    private LaboratoryDTO convertToDto(Laboratory obj){
        return modelMapper.map(obj, LaboratoryDTO.class);
    }

    // Convertir de un DTO a un Modelo (Entity)
    private Laboratory convertToEntity(LaboratoryDTO dto){
        return modelMapper.map(dto, Laboratory.class);
    }
}
