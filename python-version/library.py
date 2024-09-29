class Book:
    def __init__(self, title, author, isbn):
        self.title = title
        self.author = author
        self.isbn = isbn
        self.is_borrowed = False

    def __repr__(self):
        return f"Book('{self.title}', '{self.author}', ISBN: {self.isbn})"

class Member:
    def __init__(self, name, member_id):
        self.name = name
        self.member_id = member_id
        self.borrowed_books = []

    def borrow_book(self, book):
        if len(self.borrowed_books) < 3:
            if not book.is_borrowed:
                book.is_borrowed = True
                self.borrowed_books.append(book)
                print(f"{self.name} borrowed {book.title}")
            else:
                print(f"{book.title} is already borrowed.")
        else:
            print(f"{self.name} has already borrowed 3 books.")

    def return_book(self, book):
        if book in self.borrowed_books:
            book.is_borrowed = False
            self.borrowed_books.remove(book)
            print(f"{self.name} returned {book.title}")
        else:
            print(f"{self.name} did not borrow {book.title}.")

    def __repr__(self):
        return f"Member('{self.name}', ID: {self.member_id})"

class Library:
    def __init__(self):
        self.books = []
        self.members = []

    def add_book(self, book):
        self.books.append(book)
        print(f"Added {book.title} to the library.")

    def register_member(self, member):
        self.members.append(member)
        print(f"Registered {member.name} as a library member.")

    def list_books(self):
        print("\nLibrary Books:")
        for book in self.books:
            status = "Available" if not book.is_borrowed else "Borrowed"
            print(f"{book.title} by {book.author} - {status}")
        print()

    def list_members(self):
        print("\nLibrary Members:")
        for member in self.members:
            print(f"{member.name} (ID: {member.member_id})")
        print()
