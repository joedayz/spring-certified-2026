package com.spring.professional.exam.tutorial.module04.guide42;

import com.spring.professional.exam.tutorial.module04.guide42.entity.Book;
import com.spring.professional.exam.tutorial.module04.guide42.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Guía 4.2 - Slice testing: @DataJpaTest.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryDataJpaTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void saveAndFindById() {
        Book book = new Book(null, "Effective Java", "Joshua Bloch", "978-0134685991");
        Book saved = bookRepository.save(book);
        entityManager.flush();
        entityManager.clear();

        Book found = bookRepository.findById(saved.getId()).orElseThrow(() -> new AssertionError("Not found"));
        assertThat(found.getTitle()).isEqualTo("Effective Java");
        assertThat(found.getAuthor()).isEqualTo("Joshua Bloch");
    }

    @Test
    public void findByAuthor() {
        entityManager.persistAndFlush(new Book(null, "Book One", "Author A", "ISBN-1"));
        entityManager.persistAndFlush(new Book(null, "Book Two", "Author A", "ISBN-2"));
        entityManager.persistAndFlush(new Book(null, "Other", "Author B", "ISBN-3"));

        List<Book> byAuthor = bookRepository.findByAuthor("Author A");
        assertThat(byAuthor).hasSize(2);
        assertThat(byAuthor).extracting(Book::getTitle).containsExactlyInAnyOrder("Book One", "Book Two");
    }
}
