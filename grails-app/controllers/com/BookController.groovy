package com

import library.Book
import grails.validation.ValidationException
import grails.converters.JSON

class BookController {

    def bookService

    def index() {}

    def create() {
        def params = request.JSON
        Book book = new Book(
            name: params.name,
            author: params.author
        )

        if (book.save()) {
            render status: 201, text: "Livro criado com sucesso"
        } else {
            render status: 400, text: "Erro ao criar o livro"
        }
    }

    def list() {
        def books = Book.list().collect { book ->
            [name: book.name, author: book.author]
        }
        render books as JSON
    }

    def update() {
        try {
            def params = request.JSON
            System.out.println(params);
           // Book book = bookService.update(params)
            Book book = Book.get(params.id as Long)
            book.name = params.name
            book.author = params.author
            book.save(failOnError: true)
            render([success: true] as JSON)
        } catch(Exception e) {
            render([success: false, message: message(code: "Ocorreu um erro ao atualizar o livro")] as JSON)
        }
    }

    def delete() {
        try {
            def params = request.JSON
            Book book = Book.get(params.id as Long)
            if (!book) {
                render([success: false, message: "Livro não encontrado"] as JSON)
                return
            }
            book.delete(flush: true)
            render([success: true] as JSON)
        } catch (Exception e) {
            render([success: false, message: "Ocorreu um erro ao excluir o livro"] as JSON)
        }
    }
}
