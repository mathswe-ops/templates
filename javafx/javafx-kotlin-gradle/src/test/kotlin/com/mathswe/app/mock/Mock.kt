package com.mathswe.app.mock

import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.junit.jupiter.api.Assertions.assertEquals

class BookServiceTest {
    private val books = listOf(
        Book("Book A", "Author 1", "123"),
        Book("Book B", "Author 2", "456"),
        Book("Book C", "Author 1", "789")
    )

    private val repository: BookRepository = mock()
    private val service = BookService(repository)

    @Test
    fun `should return books by specific author`() {
        `when`(repository.getAll()).thenReturn(books)

        val result = service.getBooksByAuthor("Author 1")

        // Verifying that the repository's getAll method was called once
        verify(repository, times(1)).getAll()

        // Asserting that the result contains only books by Author 1
        assertEquals(2, result.size)
        assertEquals(listOf(books[0], books[2]), result)
    }

    @Test
    fun `should return an empty list when author has no books`() {
        `when`(repository.getAll()).thenReturn(books)

        val result = service.getBooksByAuthor("Unknown Author")

        verify(repository, times(1)).getAll()

        assertEquals(0, result.size)
    }
}
