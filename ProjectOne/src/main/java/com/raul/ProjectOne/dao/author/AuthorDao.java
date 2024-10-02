package com.raul.ProjectOne.dao.author;

import org.springframework.jdbc.core.JdbcTemplate;
import com.raul.ProjectOne.dao.types.author.AuthorInterface;
import com.raul.ProjectOne.domain.author.Author;

public class AuthorDao implements AuthorInterface {

  private final JdbcTemplate jdbcTemplate;

  public AuthorDao(final JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void create(Author author) {
    jdbcTemplate.update(
        "INSERT INTO authors (id, name, age) VALUES ($1, $2, $3)",
        author.getId(), author.getName(), author.getAge());
  }

}
