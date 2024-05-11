package com.library.management.system.service;

import com.library.management.system.model.dto.PatronDto;

import java.util.List;


public interface PatronService {

    public List<PatronDto> getAllPatrons();

    public List<PatronDto> getAllPatronsPaginated(int page, int size);

    public PatronDto getPatronById(Long id);

    public PatronDto savePatron(PatronDto patronDto);

    public void updatePatron(Long id, PatronDto patronDto);

    public void deletePatron(Long id);

}
