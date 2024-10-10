package com.mathswe.app.mock

data class Book(val title: String, val author: String, val isbn: String)

interface BookRepository {
    fun getAll(): List<Book>
}

class BookService(private val repository: BookRepository) {
    fun getBooksByAuthor(authorName: String): List<Book> = repository
        .getAll()
        .filter { it.author == authorName }
}
