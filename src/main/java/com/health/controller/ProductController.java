package com.health.controller;

import com.health.dto.MedicDTO;
import com.health.dto.ProductDTO;
import com.health.model.Medic;
import com.health.model.Product;
import com.health.service.IMedicService;
import com.health.service.IProductService;
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
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService service;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll() throws Exception{
        List<ProductDTO> list = service.findAll().stream().map(this::convertToDto).toList(); // e -> convertToDto(e)
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO>  findById(@PathVariable("id") Integer id) throws Exception{
        ProductDTO obj = convertToDto(service.findById(id)) ;
        return ResponseEntity.ok(obj);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> save(@Valid @RequestBody ProductDTO dto) throws Exception{
        Product obj = service.save(convertToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdProduct()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody ProductDTO dto) throws Exception{
        Product entity = convertToEntity(dto);
        entity.setIdProduct(id);
        Product obj =  service.update(entity, id);
        return ResponseEntity.ok(convertToDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Convertir de un Modelo a un DTO
    private ProductDTO convertToDto(Product obj){
        return modelMapper.map(obj, ProductDTO.class);
    }

    // Convertir de un DTO a un Modelo (Entity)
    private Product convertToEntity(ProductDTO dto){
        return modelMapper.map(dto, Product.class);
    }
}
