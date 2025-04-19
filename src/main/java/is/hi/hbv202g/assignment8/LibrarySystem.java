package is.hi.hbv202g.assignment8;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The LibrarySystem class manages users, books, book series, and their lendings.
 * It supports functionality for adding users and books, lending and returning books,
 * and searching for books or users by name or title.
 */
public class LibrarySystem {
    private final List<User> users = new ArrayList<>();
    private final List<Lending> lendings = new ArrayList<>();
    private final List<Book> books = new ArrayList<>();
    private final List<BookSeries> bookSeriesList = new ArrayList<>();

    /**
     * Constructs a new LibrarySystem.
     */
    public LibrarySystem() {
    }

    /**
     * Lists all books and book series in the library.
     */
    public void listAllBooks() {
        if (books.isEmpty() && bookSeriesList.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            System.out.println("\n--- All Books in Library (includes those in series) ---");
            for (Book book : books) {
                System.out.println("- " + book.getTitle() + ", by " + book.getAuthors()
                        + "\t -" + (book.isAvailable() ? "available" : "unavailable"));
            }

            System.out.println("\n--- All Book Series ---");
            for (BookSeries series : bookSeriesList) {
                System.out.println("- " + series.getTitle() + ", by " + series.getAuthors()
                        + "\t " + (series.isAvailable() ? "available" : "unavailable"));
            }
        }
    }

    /**
     * Adds a book with a title and a single author to the library.
     *
     * @param title      the title of the book
     * @param authorName the name of the book's author
     */
    public void addBookWithTitleAndNameOfSingleAuthor(String title, String authorName) {
        books.add(new Book(title, authorName));
    }

    /**
     * Adds a book with a title and a list of authors to the library.
     *
     * @param title   the title of the book
     * @param authors the list of authors
     * @throws EmptyAuthorListException if the author list is empty
     */
    public void addBookWithTitleAndAuthorList(String title, List<Author> authors) throws EmptyAuthorListException {
        books.add(new Book(title, authors));
    }

    /**
     * Adds a book series with a title and a single author.
     * Books in the series are also added to the library individually.
     *
     * @param seriesTitle the title of the book series
     * @param books       the books in the series
     * @param authorName  the name of the author
     * @throws EmptyAuthorListException     if the author list is empty
     * @throws BookSeriesNotASeriesException if the books do not form a valid series
     */
    public void addBookSeriesWithTitleAndNameOfSingleAuthor(String seriesTitle, List<Book> books, String authorName)
            throws EmptyAuthorListException, BookSeriesNotASeriesException {
        BookSeries series = new BookSeries(seriesTitle, books, authorName);
        bookSeriesList.add(series);
        this.books.addAll(series.getBooks());
    }

    /**
     * Adds a book series with a title and a list of authors.
     * Books in the series are also added to the library individually.
     *
     * @param seriesTitle the title of the book series
     * @param books       the books in the series
     * @param authors     the list of authors
     * @throws EmptyAuthorListException     if the author list is empty
     * @throws BookSeriesNotASeriesException if the books do not form a valid series
     */
    public void addBookSeriesWithTitleAndAuthorList(String seriesTitle, List<Book> books, List<Author> authors)
            throws EmptyAuthorListException, BookSeriesNotASeriesException {
        BookSeries series = new BookSeries(seriesTitle, books, authors);
        bookSeriesList.add(series);
        this.books.addAll(series.getBooks());
    }

    /**
     * Adds a student user to the library system.
     *
     * @param name    the student's name
     * @param feePaid whether the student has paid the lending fee
     */
    public void addStudentUser(String name, boolean feePaid) {
        users.add(new Student(name, feePaid));
    }

    /**
     * Adds a faculty member to the library system.
     *
     * @param name       the faculty member's name
     * @param department the department the faculty member belongs to
     */
    public void addFacultyMemberUser(String name, String department) {
        users.add(new FacultyMember(name, department));
    }

    /**
     * Finds a book in the library by title.
     *
     * @param title the title of the book
     * @return the Book object
     * @throws UserOrBookDoesNotExistException if the book cannot be found
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
     * Finds a book series in the library by title.
     *
     * @param title the title of the book series
     * @return the BookSeries object
     * @throws UserOrBookDoesNotExistException if the book series cannot be found
     */
    public BookSeries findBookSeriesByTitle(String title) throws UserOrBookDoesNotExistException {
        for (BookSeries bookSeries : bookSeriesList) {
            if (bookSeries.getTitle().equalsIgnoreCase(title)) {
                return bookSeries;
            }
        }
        throw new UserOrBookDoesNotExistException("Book series could not be found");
    }

    /**
     * Finds a user in the library system by name.
     *
     * @param name the name of the user
     * @return the User object
     * @throws UserOrBookDoesNotExistException if the user cannot be found
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
     * Borrows a book for a user.
     *
     * @param user the user borrowing the book
     * @param book the book to borrow
     * @throws UserOrBookDoesNotExistException if the user or book does not exist
     */
    public void borrowBook(User user, Book book) throws UserOrBookDoesNotExistException {
        if (!users.contains(user)) {
            throw new UserOrBookDoesNotExistException("User could not be found");
        }

        if (!books.contains(book)) {
            throw new UserOrBookDoesNotExistException("Book could not be found");
        }

        if (book.isAvailable()) {
            Lending lending = new Lending(book, user);
            lendings.add(lending);
            System.out.println(book.getTitle() + " lent to " + user.getName() + " successfully!");
        } else {
            System.out.println("Book is already being loaned");
        }
    }

    /**
     * Borrows a book series for a user.
     *
     * @param user       the user borrowing the series
     * @param bookSeries the book series to borrow
     * @throws UserOrBookDoesNotExistException if the user or series does not exist
     */
    public void borrowBookSeries(User user, BookSeries bookSeries) throws UserOrBookDoesNotExistException {
        if (!users.contains(user)) {
            throw new UserOrBookDoesNotExistException("User could not be found");
        }

        if (!bookSeriesList.contains(bookSeries)) {
            throw new UserOrBookDoesNotExistException("Book series could not be found");
        }

        if (bookSeries.isAvailable()) {
            Lending lending = new Lending(bookSeries, user);
            lendings.add(lending);
            System.out.println(bookSeries.getTitle() + " lent to " + user.getName() + " successfully!");
        } else {
            System.out.println("Book series is already being loaned");
        }
    }

    /**
     * Extends the lending period for a book by a faculty member.
     *
     * @param facultyMember the faculty member
     * @param book the book to extend
     * @param daysToExtend the number of days to extend
     * @throws UserOrBookDoesNotExistException if the book does not exist
     */
    public void extendLendingOfSingleBook(FacultyMember facultyMember, Book book, int daysToExtend)
            throws UserOrBookDoesNotExistException {
        if (!books.contains(book)) {
            throw new UserOrBookDoesNotExistException("Book could not be found");
        }

        if (!users.contains(facultyMember)) {
            System.out.println("Faculty member not found, lending failed");
            return;
        }

        for (Lending lends : lendings) {
            if (lends.getBook().equals(book)) {
                lends.setDueDate(lends.getDueDate().plusDays(daysToExtend));
                System.out.println("New due date is " + lends.getDueDate());
                return;
            }
        }

        System.out.println("Book was not being lent. Please lend the book first before extending lending period");
    }

    /**
     * Extends the lending period for a book series by a faculty member.
     *
     * @param facultyMember the faculty member
     * @param bookSeries the book series to extend
     * @param daysToExtend the number of days to extend
     * @throws UserOrBookDoesNotExistException if the series does not exist
     */
    public void extendLendingOfBookSeries(FacultyMember facultyMember, BookSeries bookSeries, int daysToExtend)
            throws UserOrBookDoesNotExistException {
        if (!bookSeriesList.contains(bookSeries)) {
            throw new UserOrBookDoesNotExistException("Book series could not be found");
        }

        if (!users.contains(facultyMember)) {
            System.out.println("Faculty member not found, lending failed");
            return;
        }

        for (Lending lends : lendings) {
            if (lends.getBookSeries().equals(bookSeries)) {
                lends.setDueDate(lends.getDueDate().plusDays(daysToExtend));
                System.out.println("New due date is " + lends.getDueDate());
                return;
            }
        }

        System.out.println("Book series was not being lent. Please lend the series first before extending the period");
    }

    /**
     * Returns a book from a user.
     *
     * @param user the user returning the book
     * @param book the book to return
     * @throws UserOrBookDoesNotExistException if the user or book does not exist
     */
    public void returnBook(User user, Book book) throws UserOrBookDoesNotExistException {
        if (!users.contains(user)) {
            throw new UserOrBookDoesNotExistException("User could not be found");
        }

        if (!books.contains(book)) {
            throw new UserOrBookDoesNotExistException("Book could not be found");
        }

        for (Lending lends : lendings) {
            lends.returnBook();
            lendings.remove(lends);
            System.out.println("Book " + book.getTitle() + " returned by " + user.getName() + ".");
            return;
        }

        System.out.println("Book " + book.getTitle() + " was not registered as being lent to " + user.getName());
    }

    /**
     * Returns a book series from a user.
     *
     * @param user the user returning the book series
     * @param bookSeries the book series to return
     * @throws UserOrBookDoesNotExistException if the user or series does not exist
     */
    public void returnBookSeries(User user, BookSeries bookSeries) throws UserOrBookDoesNotExistException {
        if (!users.contains(user)) {
            throw new UserOrBookDoesNotExistException("User could not be found");
        }

        if (!bookSeriesList.contains(bookSeries)) {
            throw new UserOrBookDoesNotExistException("Book series could not be found");
        }

        for (Lending lends : lendings) {
            lends.returnBookSeries();
            lendings.remove(lends);
            System.out.println("Book series " + bookSeries.getTitle() + " returned by " + user.getName() + ".");
            return;
        }

        System.out.println("Book series " + bookSeries.getTitle() + " was not registered as being lent to " + user.getName());
    }
}
