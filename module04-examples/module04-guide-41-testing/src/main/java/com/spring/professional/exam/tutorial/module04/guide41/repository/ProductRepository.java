package com.spring.professional.exam.tutorial.module04.guide41.repository;

import com.spring.professional.exam.tutorial.module04.guide41.dto.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private static final RowMapper<Product> ROW_MAPPER = (rs, rowNum) ->
            new Product(rs.getLong("id"), rs.getString("name"), rs.getDouble("price"));

    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> findAll() {
        return jdbcTemplate.query("SELECT id, name, price FROM product ORDER BY id", ROW_MAPPER);
    }

    public Optional<Product> findById(Long id) {
        List<Product> list = jdbcTemplate.query("SELECT id, name, price FROM product WHERE id = ?", ROW_MAPPER, id);
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }
}
