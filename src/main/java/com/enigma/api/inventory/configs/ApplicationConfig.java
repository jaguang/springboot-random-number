package com.enigma.api.inventory.configs;

import com.enigma.api.inventory.entities.Item;
import com.enigma.api.inventory.entities.Stock;
import com.enigma.api.inventory.entities.Unit;
import com.enigma.api.inventory.models.ItemRequest;
import com.enigma.api.inventory.models.ItemResponse;
import com.enigma.api.inventory.models.StockRequest;
import com.enigma.api.inventory.models.UnitModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    // anotasi controller , service , component , configuration melekat pada kelas
    // anotasi bean melekat ke kelas
    @Bean
    ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.typeMap(UnitModel.class, Unit.class).addMappings(mapper -> {
            mapper.skip(Unit::setId);
        });

        return modelMapper;
    }
}
