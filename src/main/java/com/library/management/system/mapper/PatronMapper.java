package com.library.management.system.mapper;

import com.library.management.system.model.dto.BookDto;
import com.library.management.system.model.dto.PatronDto;
import com.library.management.system.model.entity.Book;
import com.library.management.system.model.entity.Patron;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PatronMapper {

    PatronMapper MAPPER = Mappers.getMapper(PatronMapper.class);

    @Mapping(target = "borrowingRecords", ignore = true)
    Patron mapPatronDtoToPatron(PatronDto bookDto);
}
