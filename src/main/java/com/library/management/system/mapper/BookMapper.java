package com.library.management.system.mapper;

import com.library.management.system.model.dto.BookDto;
import com.library.management.system.model.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookMapper MAPPER = Mappers.getMapper(BookMapper.class);

    @Mapping(target = "borrowingRecords", ignore = true)
    Book mapBookDtoToBook(BookDto bookDto);
}
