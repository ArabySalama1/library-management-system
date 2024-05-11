package com.library.management.system.service;

import com.library.management.system.model.dto.BookDto;

import java.util.List;

public interface BookService {

    public List<BookDto> getAllBooks();

    public List<BookDto> getAllBooksPaginated(int page, int size);

    public BookDto getBookById(Long id);

    public BookDto saveBook(BookDto bookDto);

    public void updateBook(Long id, BookDto bookDto);

    public void deleteBook(Long id);

}
