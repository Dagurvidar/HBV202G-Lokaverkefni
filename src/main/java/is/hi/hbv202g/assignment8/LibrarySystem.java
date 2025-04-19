package is.hi.hbv202g.assignment8;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The LibrarySystem class is responsible for managing users, books, and lendings
 * in a library system.
 */
public class LibrarySystem {
    private final List<User> users = new ArrayList<>();
    private final List<Lending> lendings = new ArrayList<>();
    private final List<Book> books = new ArrayList<>();

    public LibrarySystem() {
    }

    /**
     * Adds a book with a title and a single author to the library system.
     *
     * @param title The title of the book.
     * @param authorName The name of the author of the book.
     */
    public void addBookWithTitleAndNameOfSingleAuthor(String title, String authorName) {
        books.add(new Book(title, authorName));
    }

    /**
     * Adds a book with a title and a list of authors to the library system.
     *
     * @param title The title of the book.
     * @param authors A list of authors for the book.
     * @throws EmptyAuthorListException If the author list is empty.
     */
    public void addBookWithTitleAndAuthorList(String title, List<Author> authors) throws EmptyAuthorListException {
        if (authors == null || authors.isEmpty()) {
            throw new EmptyAuthorListException("Author list cannot be empty");
        }

        books.add(new Book(title, authors));
    }

    /**
     * Adds a student user to the system.
     *
     * @param name The name of the student.
     * @param feePaid Whether the student has paid the lending fee.
     */
    public void addStudentUser(String name, boolean feePaid) {
        users.add(new Student(name, feePaid));
    }

    /**
     * Adds a faculty member user to the system.
     *
     * @param name The name of the faculty member.
     * @param department The department of the faculty member.
     */
    public void addFacultyMemberUser(String name, String department) {
        users.add(new FacultyMember(name, department));
    }

    /**
     * Finds a book by its title in the system.
     *
     * @param title The title of the book.
     * @return The book object corresponding to the title.
     * @throws UserOrBookDoesNotExistException If the book cannot be found in the system.
     */
    public Book findBookByTitle(String title) throws UserOrBookDoesNotExistException {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }

        throw new UserOrBookDoesNotExistException("Book could not be found");
    }

    /**
     * Finds a user by their name in the system.
     *
     * @param name The name of the user.
     * @return The user object corresponding to the name.
     * @throws UserOrBookDoesNotExistException If the user cannot be found in the system.
     */
    public User findUserByName(String name) throws UserOrBookDoesNotExistException {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }

        throw new UserOrBookDoesNotExistException("User could not be found");
    }

    /**
     * Borrows a book for a user in the system.
     *
     * @param user The user borrowing the book.
     * @param book The book being borrowed.
     * @throws UserOrBookDoesNotExistException If the user or the book is not found in the system.
     */
    public void borrowBook(User user, Book book) throws UserOrBookDoesNotExistException {
        if (!users.contains(user)) {
            throw new UserOrBookDoesNotExistException("User could not be found");
        }
        if (!books.contains(book)) {
            throw new UserOrBookDoesNotExistException("Book could not be found");
        }

        Lending lending = new Lending(book, user);
        lendings.add(lending);
        System.out.println(book.getTitle() + " lent to " + user.getName() + " successfully!");
    }

    /**
     * Extends the lending period for a book by a faculty member.
     *
     * @param facultyMember The faculty member extending the lending period.
     * @param book The book whose lending period is being extended.
     * @param newDueDate The new due date for the book.
     * @throws UserOrBookDoesNotExistException If the book is not found in the system.
     */
    public void extendLending(FacultyMember facultyMember, Book book, LocalDate newDueDate) throws UserOrBookDoesNotExistException {
        if (!books.contains(book)) {
            throw new UserOrBookDoesNotExistException("Book could not be found");
        }

        for (Lending lends : lendings) {
            if (lends.getBook().equals(book)) {
                LocalDate currentDueDate = lends.getDueDate();
                lends.setDueDate(currentDueDate.plusDays(30));
                System.out.println("New due date is " + lends.getDueDate());
                return;
            }
        }

        System.out.println("Book was not being lent. Please lend the book first before extending lending period");
    }

    /**
     * Returns a book from a user.
     *
     * @param user The user returning the book.
     * @param book The book being returned.
     * @throws UserOrBookDoesNotExistException If the user or the book is not found in the system.
     */
    public void returnBook(User user, Book book) throws UserOrBookDoesNotExistException {
        if (!users.contains(user)) {
            throw new UserOrBookDoesNotExistException("User could not be found");
        }

        if (!books.contains(book)) {
            throw new UserOrBookDoesNotExistException("Book could not be found");
        }

        for (Lending lends : lendings) {
            lendings.remove(lends);
            System.out.println("Book " + book.getTitle() + " returned by " + user.getName() + ".");
            return;
        }

        System.out.println("Book " + book.getTitle() + " was not registered as being lent to " + user.getName());
    }
}
