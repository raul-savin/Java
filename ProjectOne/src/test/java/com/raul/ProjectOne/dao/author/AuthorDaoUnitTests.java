package com.raul.ProjectOne.dao.author;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import com.raul.ProjectOne.domain.author.Author;
import com.raul.ProjectOne.utils.author.AuthorTestDataUtil;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoUnitTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AuthorDao underTest;

    @Test
    public void testThatCreateAuthorGeneratesCorrectSql() {
        Author author = AuthorTestDataUtil.createTestAuthor();

        underTest.create(author);

        verify(jdbcTemplate).update(
                eq("INSERT INTO authors (id, name, age) VALUES ($1, $2, $3)"),
                eq(1L), eq("John Doe"), eq(42));
    }

    @Test
    public void testThatFindOneGeneratesTheCorrectSql() {
        underTest.findOne(1L);
        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors WHERE id = $1 LIMIT 1"),
                ArgumentMatchers.<AuthorDao.AuthorRowMapper>any(),
                eq(1L));
    }
}
