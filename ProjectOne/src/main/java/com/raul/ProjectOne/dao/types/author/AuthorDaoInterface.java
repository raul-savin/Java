package com.raul.ProjectOne.dao.types.author;

import java.util.Optional;

import com.raul.ProjectOne.domain.author.Author;

public interface AuthorDaoInterface {
  void create(Author author);

  Optional<Author> findOne(long l);
}
