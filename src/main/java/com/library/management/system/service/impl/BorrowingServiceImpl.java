package com.library.management.system.service.impl;

import com.library.management.system.mapper.BookMapper;
import com.library.management.system.mapper.PatronMapper;
import com.library.management.system.model.dto.BookDto;
import com.library.management.system.model.dto.PatronDto;
import com.library.management.system.model.entity.BorrowingRecord;
import com.library.management.system.repository.BorrowingRecordRepository;
import com.library.management.system.service.BorrowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BorrowingServiceImpl implements BorrowingService {

    private final BorrowingRecordRepository borrowingRecordRepository;

    private final BookServiceImpl bookServiceImpl;
    private final PatronServiceImpl patronServiceImpl;

    private final PatronMapper patronMapper;
    private final BookMapper bookMapper;

    @Override
    public void borrowBook(Long bookId, Long patronId) {

        BorrowingRecord borrowingRecord = new BorrowingRecord();
        BookDto bookDto = bookServiceImpl.getBookById(bookId);
        PatronDto patronDto = patronServiceImpl.getPatronById(patronId);
        borrowingRecord.setBook(bookMapper.mapBookDtoToBook(bookDto));
        borrowingRecord.setPatron(patronMapper.mapPatronDtoToPatron(patronDto));
        borrowingRecord.setBorrowingDate(LocalDate.now());
        borrowingRecordRepository.save(borrowingRecord);
    }

    @Override
    public void recordReturn(Long bookId, Long patronId) {
        Optional<BorrowingRecord> borrowingRecordOp = borrowingRecordRepository.findByBookIdAndPatronId(bookId, patronId);
        if (borrowingRecordOp.isPresent()) {
            BorrowingRecord borrowingRecord = borrowingRecordOp.get();
            borrowingRecord.setReturnDate(LocalDate.now());
            borrowingRecordRepository.save(borrowingRecord);
        }
    }


}
