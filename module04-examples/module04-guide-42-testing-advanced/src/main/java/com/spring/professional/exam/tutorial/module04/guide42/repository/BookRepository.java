package com.spring.professional.exam.tutorial.module04.guide42.repository;

import com.spring.professional.exam.tutorial.module04.guide42.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthor(String author);
}
