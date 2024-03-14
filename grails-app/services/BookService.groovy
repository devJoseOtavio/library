package com

import library.Book

class BookService {

    public Book update(Map params) {
        Book book = Book.get(params.id as Long)
        if (!book) return null
        book.name = params.name
        book.author = params.author
        book.save(failOnError: true)
        return book
    }
}