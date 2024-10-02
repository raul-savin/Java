package com.raul.ProjectOne.dao.book;

import org.springframework.jdbc.core.JdbcTemplate;
import com.raul.ProjectOne.dao.types.book.BookInterface;
import com.raul.ProjectOne.domain.book.Book;

public class BookDao implements BookInterface {

  private final JdbcTemplate jdbcTemplate;

  public BookDao(final JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void create(Book book) {
    jdbcTemplate.update(
        "INSERT INTO authors (isbn, title, author_id) VALUES ($1, $2, $3)",
        book.getIsbn(), book.getTitle(), book.getAuthorId());
  }

}
