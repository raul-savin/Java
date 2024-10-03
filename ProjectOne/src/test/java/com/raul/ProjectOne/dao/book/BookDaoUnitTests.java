package com.raul.ProjectOne.dao.book;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import com.raul.ProjectOne.domain.book.Book;
import com.raul.ProjectOne.utils.book.BookTestDataUtil;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookDaoUnitTests {

  @Mock
  private JdbcTemplate jdbcTemplate;

  @InjectMocks
  private BookDao underTest;

  @Test
  public void testThatCreateBookGeneratesCorrectSql() {
    Book book = BookTestDataUtil.createTestBook();

    underTest.create(book);

    verify(jdbcTemplate).update(
        eq("INSERT INTO books (isbn, title, author_id) VALUES ($1, $2, $3)"),
        eq("1"),
        eq("title"),
        eq(1L));
  }

  @Test
  public void testThatFindOneBookGeneratesCorrectSql() {
    underTest.find("1");
    verify(jdbcTemplate).query(
        eq("SELECT isbn, title, author_id from books WHERE isbn = $1 LIMIT 1"),
        ArgumentMatchers.<BookDao.BookRowMapper>any(),
        eq("1"));
  }

}
