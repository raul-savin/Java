package com.raul.ProjectOne.dao.book;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.raul.ProjectOne.dao.types.book.BookDaoInterface;
import com.raul.ProjectOne.domain.book.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class BookDao implements BookDaoInterface {

  private final JdbcTemplate jdbcTemplate;

  public BookDao(final JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void create(Book book) {
    jdbcTemplate.update(
        "INSERT INTO books (isbn, title, author_id) VALUES ($1, $2, $3)",
        book.getIsbn(),
        book.getTitle(),
        book.getAuthorId());
  }

  @Override
  public Optional<Book> find(String isbn) {
    List<Book> results = jdbcTemplate.query(
        "SELECT isbn, title, author_id from books WHERE isbn = $1 LIMIT 1",
        new BookRowMapper(),
        isbn);
    return results.stream().findFirst();
  }

  public static class BookRowMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
      return Book.builder()
          .isbn(rs.getString("isbn"))
          .title(rs.getString("title"))
          .authorId(rs.getLong("author_id"))
          .build();
    }

  }
}
