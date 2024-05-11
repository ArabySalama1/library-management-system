package com.library.management.system.controller;

import com.library.management.system.model.dto.BookDto;
import com.library.management.system.model.dto.PatronDto;
import com.library.management.system.service.PatronService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patrons")
@RequiredArgsConstructor
@Tag(name = "Patron endpoints")
public class PatronController {

    private final PatronService patronService;

    @Operation(summary = "Retrieve a list of all patrons")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PatronDto>> getAllPatrons() {
        return new ResponseEntity<>(patronService.getAllPatrons(), HttpStatus.OK);
    }

    @Operation(summary = "Retrieve a list of all patrons paginated")
    @GetMapping("/page/{page}/{size}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PatronDto>> getAllBooks(@PathVariable("page") int page,
                                                     @PathVariable("size") int size) {

        return new ResponseEntity<>(patronService.getAllPatronsPaginated(page, size), HttpStatus.OK);
    }


    @Operation(summary = "Retrieve details of a specific patron by ID")
    @GetMapping("/{id}")
    public ResponseEntity<PatronDto> getPatronById(@PathVariable Long id) {
        PatronDto book = patronService.getPatronById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @Operation(summary = "Add a new patron to the system")
    @PostMapping
    public ResponseEntity<PatronDto> savePatron(@RequestBody PatronDto patronDto) {
        PatronDto savedPatron = patronService.savePatron(patronDto);
        return new ResponseEntity<>(savedPatron, HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing patron's information")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePatron(@PathVariable Long id, @RequestBody PatronDto patronDto) {
        patronService.updatePatron(id, patronDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Operation(summary = "Remove a patron from the system")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable Long id) {
        patronService.deletePatron(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
