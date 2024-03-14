package library

class Book {

    String name 

    String author

    static constraints = {
        name blank: false, unique: true
        author blank: false
    }
}
