package com.health.config;

import com.health.dto.MedicDTO;
import com.health.model.Medic;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean(name = "defaultMapper")
    public ModelMapper defaultMapper() {
        return new ModelMapper();
    }

    @Bean(name = "medicMapper")
    public ModelMapper medicMapper() {
        ModelMapper modelMapper = new ModelMapper();

        //Escritura
        modelMapper.createTypeMap(MedicDTO.class, Medic.class)
                .addMapping(MedicDTO::getFirstName, (dest, v) -> dest.setFirstName((String) v))
                .addMapping(MedicDTO::getLastName, (dest, v) -> dest.setLastName((String) v))
                .addMapping(MedicDTO::getPhoto, (dest, v) -> dest.setPhotoUrl((String) v));

        //Lectura
        modelMapper.createTypeMap(Medic.class, MedicDTO.class)
                .addMapping(Medic::getFirstName, (dest, v) -> dest.setFirstName((String) v))
                .addMapping(Medic::getLastName, (dest, v) -> dest.setLastName((String) v));

        return modelMapper;
    }
}
