package com.library.management.system.controller;

import com.library.management.system.service.BorrowingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Borrowing endpoints")
public class BorrowingController {

    private final BorrowingService borrowingService;


    @Operation(summary = "Allow a patron to borrow a book")
    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<Void> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        borrowingService.borrowBook(bookId, patronId);
        return new ResponseEntity<>(HttpStatus.OK);

    }


    @Operation(summary = "Record the return of a borrowed book by a patron")
    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<Void> recordReturn(@PathVariable Long bookId, @PathVariable Long patronId) {
        borrowingService.recordReturn(bookId, patronId);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
