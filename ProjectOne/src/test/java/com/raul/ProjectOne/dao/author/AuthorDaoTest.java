package com.raul.ProjectOne.dao.author;

import com.raul.ProjectOne.domain.author.Author;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoTest {

  @Mock
  private JdbcTemplate jdbcTemplate;

  @InjectMocks
  private AuthorDao undertest;

  @Test
  public void testThatCreateAuthorGeneratesCorectSql() {
    Author author = Author.builder()
        .id(1L)
        .name("name")
        .age(42)
        .build();

    undertest.create(author);

    verify(jdbcTemplate).update(
        eq("INSERT INTO authors (id, name, age) VALUES ($1, $2, $3)"),
        eq(1L), eq("name"), eq(42));
  }
}
