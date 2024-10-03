package com.raul.ProjectOne.dao.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.raul.ProjectOne.dao.author.AuthorDao;
import com.raul.ProjectOne.domain.author.Author;
import com.raul.ProjectOne.domain.book.Book;
import com.raul.ProjectOne.utils.author.AuthorTestDataUtil;
import com.raul.ProjectOne.utils.book.BookTestDataUtil;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookDaoIntegrationTests {

  private AuthorDao authorDao;
  private BookDao underTest;

  @Autowired
  public BookDaoIntegrationTests(BookDao underTest, AuthorDao authorDao) {
    this.underTest = underTest;
    this.authorDao = authorDao;
  }

  @Test
  public void testThatBookCanBeCreatedAndRecalled() {
    Author author = AuthorTestDataUtil.createTestAuthor();
    authorDao.create(author);
    Book book = BookTestDataUtil.createTestBook();
    book.setAuthorId(author.getId());
    underTest.create(book);
    Optional<Book> result = underTest.find(book.getIsbn());
    assertThat(result).isPresent();
    assertThat(result.get()).isEqualTo(book);
  }
}
