//package com.example.appointmentapp.controller;
package com.health.controller;

import com.health.dto.CategoryDTO;
import com.health.dto.MedicDTO;
import com.health.model.Category;
import com.health.model.Medic;
import com.health.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.net.URI;
import java.util.List;
import jakarta.validation.Valid;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
//@CrossOrigin(origins = "*")
public class CategoryController {

    private final ICategoryService service;

    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() throws Exception{
        List<CategoryDTO> list = service.findAll().stream().map(this::convertToDto).toList(); // e -> convertToDto(e)
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO>  findById(@PathVariable("id") Integer id) throws Exception{
        CategoryDTO obj = convertToDto(service.findById(id)) ;
        return ResponseEntity.ok(obj);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> save(@Valid @RequestBody CategoryDTO dto) throws Exception{
        Category obj = service.save(convertToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdCategory()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody CategoryDTO dto) throws Exception{
        Category entity = convertToEntity(dto);
        entity.setIdCategory(id);
        Category obj =  service.update(entity, id);
        return ResponseEntity.ok(convertToDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Convertir de un Modelo a un DTO
    private CategoryDTO convertToDto(Category obj){
        return modelMapper.map(obj, CategoryDTO.class);
    }

    // Convertir de un DTO a un Modelo (Entity)
    private Category convertToEntity(CategoryDTO dto){
        return modelMapper.map(dto, Category.class);
    }

}
