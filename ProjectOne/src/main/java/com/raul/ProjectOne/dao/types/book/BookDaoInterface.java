package com.raul.ProjectOne.dao.types.book;

import java.util.Optional;

import com.raul.ProjectOne.domain.book.Book;

public interface BookDaoInterface {
  void create(Book book);

  Optional<Book> find(String isbn);
}
