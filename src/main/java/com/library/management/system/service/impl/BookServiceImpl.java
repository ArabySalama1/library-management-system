package com.library.management.system.service.impl;

import com.library.management.system.mapper.BookDtoMapper;
import com.library.management.system.mapper.BookMapper;
import com.library.management.system.model.dto.BookDto;
import com.library.management.system.model.entity.Book;
import com.library.management.system.repository.BookRepository;
import com.library.management.system.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookDtoMapper bookDtoMapper;
    private static final String ISBN_REGEX = "^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$|97[89][0-9]{10}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)(?:97[89][- ]?)?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$";


    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(bookDtoMapper::mapBookToBookDto).toList();
    }

    @Override
    public List<BookDto> getAllBooksPaginated(int page, int size) {

        PageRequest pageReq
                = PageRequest.of(page, size);

        Page<Book> books = bookRepository.findAll(pageReq);
        return books.getContent().stream().map(bookDtoMapper::mapBookToBookDto).toList();

    }

    @Override
    public BookDto getBookById(Long id) {
        Optional<Book> bookOp = bookRepository.findById(id);

        if (bookOp.isEmpty()) {
            throw new IllegalArgumentException("Book ID doesn't match");
        }

        return bookDtoMapper.mapBookToBookDto(bookOp.get());
    }

    @Override
    public BookDto saveBook(BookDto bookDto) {

        if(!Pattern.matches(ISBN_REGEX, bookDto.getIsbn())){
            throw new IllegalArgumentException("Invalid ISBN code");
        }

        Book book = bookRepository.save(bookMapper.mapBookDtoToBook(bookDto));
        return bookDtoMapper.mapBookToBookDto(book);
    }

    @Override
    public void updateBook(Long id, BookDto bookDto) {
        getBookById(id);
        bookDto.setId(id);
        bookRepository.save(bookMapper.mapBookDtoToBook(bookDto));
    }

    @Override
    public void deleteBook(Long id) {
        getBookById(id);
        bookRepository.deleteById(id);
    }

}
