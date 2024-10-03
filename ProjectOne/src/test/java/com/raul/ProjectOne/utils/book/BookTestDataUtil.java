package com.raul.ProjectOne.utils.book;

import com.raul.ProjectOne.domain.book.Book;

public class BookTestDataUtil {
  private BookTestDataUtil() {
  }

  public static Book createTestBook() {
    return Book.builder()
        .isbn("1")
        .title("title")
        .authorId(1L)
        .build();
  }
}
