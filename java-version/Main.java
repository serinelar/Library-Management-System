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
}

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        // Add books
        Book book1 = new Book("1984", "George Orwell", "ISBN001");
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", "ISBN002");

        library.addBook(book1);
        library.addBook(book2);

        // Register members
        Member member1 = new Member("Alice", "M001");

        library.registerMember(member1);

        // Borrow books
        member1.borrowBook(book1);

        // Return books
        member1.returnBook(book1);

        library.listBooks();
    }
}
