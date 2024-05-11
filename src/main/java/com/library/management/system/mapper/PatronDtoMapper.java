package com.library.management.system.mapper;

import com.library.management.system.model.dto.PatronDto;
import com.library.management.system.model.entity.Patron;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PatronDtoMapper {

    PatronDtoMapper MAPPER = Mappers.getMapper(PatronDtoMapper.class);

    PatronDto mapPatronToPatronDto(Patron patron);
}
