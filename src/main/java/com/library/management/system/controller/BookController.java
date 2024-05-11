package com.library.management.system.controller;

import com.library.management.system.model.dto.BookDto;
import com.library.management.system.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@Tag(name = "Book endpoints")
public class BookController {

    private final BookService bookService;


    @Operation(summary = "Retrieve a list of all books")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<BookDto>> getAllBooks() {

        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }


    @Operation(summary = "Retrieve a list of all books paginated")
    @GetMapping("/page/{page}/{size}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<BookDto>> getAllBooks(@PathVariable int page,
                                                     @PathVariable("size") int size) {

        return new ResponseEntity<>(bookService.getAllBooksPaginated(page, size), HttpStatus.OK);
    }


    @Operation(summary = "Retrieve details of a specific book by ID")
    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {

        BookDto book = bookService.getBookById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }


    @Operation(summary = "Add a new book to the library")
    @PostMapping
    public ResponseEntity<BookDto> saveBook(@RequestBody BookDto bookDto) {

        BookDto savedBook = bookService.saveBook(bookDto);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }


    @Operation(summary = "Update an existing book's information")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {

        bookService.updateBook(id, bookDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Operation(summary = "Remove a book from the library")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {

        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
