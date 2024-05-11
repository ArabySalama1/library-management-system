package com.library.management.system.service;

public interface BorrowingService {
    
    public void borrowBook(Long bookId, Long patronId);

    public void recordReturn(Long bookId, Long patronId);


}
