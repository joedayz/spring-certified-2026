package com.spring.professional.exam.tutorial.module04.guide42;

import com.spring.professional.exam.tutorial.module04.guide42.controller.BookController;
import com.spring.professional.exam.tutorial.module04.guide42.entity.Book;
import com.spring.professional.exam.tutorial.module04.guide42.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Guía 4.2 - Testing Avanzado: MockMVC y @WebMvcTest.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookRepository bookRepository;

    @Test
    public void getAll_returnsList() throws Exception {
        Book book = new Book(1L, "Clean Code", "Robert Martin", "978-0132350884");
        when(bookRepository.findAll()).thenReturn(List.of(book));

        mockMvc.perform(get("/api/books").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].title").value("Clean Code"))
                .andExpect(jsonPath("$[0].author").value("Robert Martin"));
    }

    @Test
    public void getById_existing_returns200() throws Exception {
        Book book = new Book(1L, "Clean Code", "Robert Martin", "978-0132350884");
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        mockMvc.perform(get("/api/books/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Clean Code"));
    }

    @Test
    public void getById_notFound_returns404() throws Exception {
        when(bookRepository.findById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/books/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void create_returns201AndLocation() throws Exception {
        Book toCreate = new Book(null, "New Book", "Author", "ISBN-123");
        Book saved = new Book(1L, "New Book", "Author", "ISBN-123");
        when(bookRepository.save(any(Book.class))).thenReturn(saved);

        ResultActions result = mockMvc.perform(
                post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(toCreate))
        );
        result.andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", containsString("/api/books/1")))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("New Book"));
    }
}
