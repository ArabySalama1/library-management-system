package com.library.management.system.repository;

import com.library.management.system.model.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    default Page<Book> findByUser(Pageable pageReq) {
        return findAll(pageReq);
    }
}

