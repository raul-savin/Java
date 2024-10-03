package com.raul.ProjectOne.utils.author;

import com.raul.ProjectOne.domain.author.Author;

public final class AuthorTestDataUtil {
  private AuthorTestDataUtil() {
  }

  public static Author createTestAuthor() {
    return Author.builder()
        .id(1L)
        .name("John Doe")
        .age(42)
        .build();
  }
}
