from library import Book, Member, Library

def main():
    library = Library()

    # Adding Books to the Library
    book1 = Book("1984", "George Orwell", "ISBN001")
    book2 = Book("The Great Gatsby", "F. Scott Fitzgerald", "ISBN002")
    book3 = Book("To Kill a Mockingbird", "Harper Lee", "ISBN003")

    library.add_book(book1)
    library.add_book(book2)
    library.add_book(book3)

    # Registering Members
    member1 = Member("Alice", "M001")
    member2 = Member("Bob", "M002")

    library.register_member(member1)
    library.register_member(member2)

    # Listing Books and Members
    library.list_books()
    library.list_members()

    # Borrowing Books
    member1.borrow_book(book1)  # Alice borrows 1984
    member1.borrow_book(book2)  # Alice borrows The Great Gatsby
    member1.borrow_book(book3)  # Alice borrows To Kill a Mockingbird

    library.list_books()  # After borrowing

    # Returning Books
    member1.return_book(book1)  # Alice returns 1984
    library.list_books()  # After returning


if __name__ == "__main__":
    main()
