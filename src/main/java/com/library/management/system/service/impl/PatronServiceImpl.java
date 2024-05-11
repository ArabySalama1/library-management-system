package com.library.management.system.service.impl;

import com.library.management.system.mapper.PatronDtoMapper;
import com.library.management.system.mapper.PatronMapper;
import com.library.management.system.model.dto.BookDto;
import com.library.management.system.model.dto.PatronDto;
import com.library.management.system.model.entity.Book;
import com.library.management.system.model.entity.Patron;
import com.library.management.system.repository.PatronRepository;
import com.library.management.system.service.PatronService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatronServiceImpl implements PatronService {

    private final PatronRepository patronRepository;
    private final PatronMapper patronMapper;
    private final PatronDtoMapper patronDtoMapper;

    @Override
    public List<PatronDto> getAllPatrons() {
        List<Patron> patrons = patronRepository.findAll();
        return patrons.stream().map(patronDtoMapper::mapPatronToPatronDto).toList();
    }

    @Override
    public List<PatronDto> getAllPatronsPaginated(int page, int size) {

        PageRequest pageReq
                = PageRequest.of(page, size);

        Page<Patron> books = patronRepository.findAll(pageReq);
        return books.getContent().stream().map(patronDtoMapper::mapPatronToPatronDto).toList();

    }

    @Override
    public PatronDto getPatronById(Long id) {
        Optional<Patron> patronOp = patronRepository.findById(id);

        if (patronOp.isEmpty()) {
            throw new IllegalArgumentException("Patron ID doesn't match");
        }

        return patronDtoMapper.mapPatronToPatronDto(patronOp.get());
    }

    @Override
    public PatronDto savePatron(PatronDto patronDto) {
        Patron patron = patronRepository.save(patronMapper.mapPatronDtoToPatron(patronDto));
        return patronDtoMapper.mapPatronToPatronDto(patron);
    }

    @Override
    public void updatePatron(Long id, PatronDto patronDto) {
        getPatronById(id);
        patronDto.setId(id);
        patronRepository.save(patronMapper.mapPatronDtoToPatron(patronDto));
    }

    @Override
    public void deletePatron(Long id) {
        getPatronById(id);
        patronRepository.deleteById(id);
    }

}
