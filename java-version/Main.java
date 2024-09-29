import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isBorrowed;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isBorrowed = false;
    }

    public String getTitle() {
        return title;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void borrowBook() {
        if (!isBorrowed) {
            isBorrowed = true;
            System.out.println(title + " has been borrowed.");
        } else {
            System.out.println(title + " is already borrowed.");
        }
    }

    // Getter for author
    public String getAuthor() {
        return author;
    }

    public void returnBook() {
        isBorrowed = false;
        System.out.println(title + " has been returned.");
    }
}

class Member {
    private String name;
    private String memberId;
    private Book[] borrowedBooks;
    private int booksBorrowed;

    public Member(String name, String memberId) {
        this.name = name;
        this.memberId = memberId;
        this.borrowedBooks = new Book[3]; // Max 3 books
        this.booksBorrowed = 0;
    }

    // Add this getter method
    public String getName() {
        return name;
    }

    public void borrowBook(Book book) {
        if (booksBorrowed < 3 && !book.isBorrowed()) {
            borrowedBooks[booksBorrowed] = book;
            booksBorrowed++;
            book.borrowBook();
        } else {
            System.out.println("Cannot borrow more than 3 books.");
        }
    }

    public void returnBook(Book book) {
        for (int i = 0; i < booksBorrowed; i++) {
            if (borrowedBooks[i] == book) {
                book.returnBook();
                borrowedBooks[i] = null;
                booksBorrowed--;
                break;
            }
        }
    }
}


class Library {
    private Book[] books;
    private Member[] members;
    private int bookCount;
    private int memberCount;

    public Library() {
        books = new Book[100]; // Library capacity
        members = new Member[100];
        bookCount = 0;
        memberCount = 0;
    }

    public void addBook(Book book) {
        books[bookCount++] = book;
        System.out.println(book.getTitle() + " added to the library.");
    }

    public void registerMember(Member member) {
        members[memberCount++] = member;
        // Change this line to use the getter method
        System.out.println(member.getName() + " registered as a member.");
    }

    public void listBooks() {
        System.out.println("\nLibrary Books:");
        for (int i = 0; i < bookCount; i++) {
            System.out.println(books[i].getTitle());
        }
    }

    public List<Book> searchBook(String query) {
    List<Book> foundBooks = new ArrayList<>();
    query = query.toLowerCase();  // Convert the query to lowercase for case-insensitive search

    for (int i = 0; i < bookCount; i++) {
        // Convert both title and author to lowercase before comparing
        String titleLower = books[i].getTitle().toLowerCase();
        String authorLower = books[i].getAuthor().toLowerCase();

        // Check if the query is contained in either the title or the author
        if (titleLower.contains(query) || authorLower.contains(query)) {
            foundBooks.add(books[i]);
        }
    }
    return foundBooks;
}
}

public class Main {
    public static void main(String[] args) {
        // Initialize Library
        Library library = new Library();

        // Adding Books to Library
        Book book1 = new Book("1984", "George Orwell", "ISBN001");
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", "ISBN002");
        Book book3 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "ISBN003");

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        // Registering Members
        Member member1 = new Member("Alice", "M001");
        Member member2 = new Member("Bob", "M002");

        library.registerMember(member1);
        library.registerMember(member2);

        // Borrowing a Book
        System.out.println("\n--- Borrowing a Book ---");
        member1.borrowBook(book1);  // Alice borrows '1984'
        member2.borrowBook(book1);  // Bob tries to borrow '1984', but it's already borrowed

        // Returning a Book
        System.out.println("\n--- Returning a Book ---");
        member1.returnBook(book1);  // Alice returns '1984'

        // List all Books
        System.out.println("\n--- Listing All Books in Library ---");
        library.listBooks();  // Should display all books

        // Search for a Book
        System.out.println("\n--- Searching for a Book ---");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter title or author to search: ");
        String query = scanner.nextLine();
        List<Book> results = library.searchBook(query);

        if (results.isEmpty()) {
            System.out.println("No books found.");
        } else {
            System.out.println("Found books:");
            for (Book book : results) {
                System.out.println(book.getTitle() + " by " + book.getAuthor());
            }
        }
    }
}
