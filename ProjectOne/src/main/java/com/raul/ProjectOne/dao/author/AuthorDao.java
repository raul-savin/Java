package com.raul.ProjectOne.dao.author;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.raul.ProjectOne.dao.types.author.AuthorDaoInterface;
import com.raul.ProjectOne.domain.author.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class AuthorDao implements AuthorDaoInterface {

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

  @Override
  public Optional<Author> findOne(long authorId) {
    List<Author> results = jdbcTemplate.query(
        "SELECT id, name, age FROM authors WHERE id = $1 LIMIT 1",
        new AuthorRowMapper(), authorId);

    return results.stream().findFirst();
  }

  public static class AuthorRowMapper implements RowMapper<Author> {

    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
      return Author.builder()
          .id(rs.getLong("id"))
          .name(rs.getString("name"))
          .age(rs.getInt("age"))
          .build();
    }
  }

}
