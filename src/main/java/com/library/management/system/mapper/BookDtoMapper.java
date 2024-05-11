package com.library.management.system.mapper;

import com.library.management.system.model.dto.BookDto;
import com.library.management.system.model.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookDtoMapper {

    BookDtoMapper MAPPER = Mappers.getMapper(BookDtoMapper.class);

    BookDto mapBookToBookDto(Book book);
}
