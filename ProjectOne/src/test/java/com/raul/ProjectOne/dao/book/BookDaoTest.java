package com.raul.ProjectOne.dao.book;

import com.raul.ProjectOne.domain.book.Book;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookDaoTest {

  @Mock
  private JdbcTemplate jdbcTemplate;

  @InjectMocks
  private BookDao undertest;

  @Test
  public void testThatCreateAuthorGeneratesCorectSql() {
    Book book = Book.builder()
        .isbn("1")
        .title("title")
        .authorId(1L)
        .build();

    undertest.create(book);

    verify(jdbcTemplate).update(
        eq("INSERT INTO authors (isbn, title, author_id) VALUES ($1, $2, $3)"),
        eq("1"), eq("title"), eq(1L));
  }

}
