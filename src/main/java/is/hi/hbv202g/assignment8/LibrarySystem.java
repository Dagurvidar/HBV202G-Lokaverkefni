package is.hi.hbv202g.assignment8;

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
    private final List<BookSeries> bookSeriesList = new ArrayList<>();

    public LibrarySystem() {
    }

    public void listAllBooks() {
        if (books.isEmpty() && bookSeriesList.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            System.out.println("\n--- All Books in Library (includes those in series ---");
            for (Book book : books) {
                System.out.println("- " + book.getTitle() + ", by " + book.getAuthors()
                + "\t -" + (book.isAvailable() ? "available" : "unavailable"));
            }

            System.out.println("\n--- All book series ---");
            for (BookSeries series : bookSeriesList) {
                System.out.println("- " + series.getTitle() + ", by " + series.getAuthors());
            }
        }
    }

    /**
     * Adds a book with a title and a single author to the library system.
     *
     * @param title      The title of the book.
     * @param authorName The name of the author of the book.
     */
    public void addBookWithTitleAndNameOfSingleAuthor(String title, String authorName) {
        books.add(new Book(title, authorName));
    }

    /**
     * Adds a book with a title and a list of authors to the library system.
     *
     * @param title   The title of the book.
     * @param authors A list of authors for the book.
     * @throws EmptyAuthorListException If the author list is empty.
     */
    public void addBookWithTitleAndAuthorList(String title, List<Author> authors) throws EmptyAuthorListException {
        books.add(new Book(title, authors));
    }

    /**
     * Books added by this method are also added to the library as individual books.
     * It is assumed in this method that every book in a series has the same author(s).
     *
     * @param seriesTitle title of book series
     * @param books       list of books in series
     * @param authorName  of books in series
     * @throws EmptyAuthorListException author list should not be empty
     */
    public void addBookSeriesWithTitleAndNameOfSingleAuthor(String seriesTitle, List<Book> books, String authorName) throws EmptyAuthorListException, BookSeriesNotASeriesException {
        BookSeries series = new BookSeries(seriesTitle, books, authorName);
        bookSeriesList.add(series);
        this.books.addAll(series.getBooks());
    }

    /**
     * Books added by this method are also added to the library as individual books.
     * It is assumed in this method that every book in a series has the same author(s).
     *
     * @param seriesTitle title of book series
     * @param books       list of books in series
     * @param authors     of books in series
     * @throws EmptyAuthorListException author list should not be empty
     */
    public void addBookSeriesWithTitleAndAuthorList(String seriesTitle, List<Book> books, List<Author> authors) throws EmptyAuthorListException, BookSeriesNotASeriesException {
        BookSeries series = new BookSeries(seriesTitle, books, authors);
        bookSeriesList.add(series);
        this.books.addAll(series.getBooks());
    }

    /**
     * Adds a student user to the system.
     *
     * @param name    The name of the student.
     * @param feePaid Whether the student has paid the lending fee.
     */
    public void addStudentUser(String name, boolean feePaid) {
        users.add(new Student(name, feePaid));
    }

    /**
     * Adds a faculty member user to the system.
     *
     * @param name       The name of the faculty member.
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
     * Finds a book by its title in the system.
     *
     * @param title The title of the book series.
     * @return The BookSeries object corresponding to the title.
     * @throws UserOrBookDoesNotExistException If the book series cannot be found in the system.
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

        if (book.isAvailable()) {
            Lending lending = new Lending(book, user);
            lendings.add(lending);
            System.out.println(book.getTitle() + " lent to " + user.getName() + " successfully!");
        } else {
            System.out.println("Book is already being loaned");
        }
    }

    /**
     * Borrows a book for a user in the system.
     *
     * @param user       The user borrowing the book.
     * @param bookSeries The book being borrowed.
     * @throws UserOrBookDoesNotExistException If the user or the book is not found in the system.
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
     * @param facultyMember The faculty member extending the lending period.
     * @param book          The book whose lending period is being extended.
     * @param daysToExtend  Amount of days the extention period is extended by.
     * @throws UserOrBookDoesNotExistException If the book is not found in the system.
     */
    public void extendLendingOfSingleBook(FacultyMember facultyMember, Book book, int daysToExtend) throws UserOrBookDoesNotExistException {
        if (!books.contains(book)) {
            throw new UserOrBookDoesNotExistException("Book could not be found");
        }

        if (!users.contains(facultyMember)) {
            System.out.println("Faculty member not found, lending failed");
            return;
        }

        for (Lending lends : lendings) {
            if (lends.getBook().equals(book)) {
                LocalDate currentDueDate = lends.getDueDate();
                lends.setDueDate(currentDueDate.plusDays(daysToExtend));
                System.out.println("New due date is " + lends.getDueDate());
                return;
            }
        }

        System.out.println("Book was not being lent. Please lend the book first before extending lending period");
    }

    /**
     * Extends the lending period for a book by a faculty member.
     *
     * @param facultyMember The faculty member extending the lending period.
     * @param bookSeries    The book series whose lending period is being extended.
     * @param daysToExtend  Amount of days the extention period is extended by.
     * @throws UserOrBookDoesNotExistException If the book is not found in the system.
     */
    public void extendLendingOfBookSeries(FacultyMember facultyMember, BookSeries bookSeries, int daysToExtend) throws UserOrBookDoesNotExistException {
        if (!bookSeriesList.contains(bookSeries)) {
            throw new UserOrBookDoesNotExistException("Book series could not be found");
        }

        if (!users.contains(facultyMember)) {
            System.out.println("Faculty member not found, lending failed");
            return;
        }

        for (Lending lends : lendings) {
            if (lends.getBookSeries().equals(bookSeries)) {
                LocalDate currentDueDate = lends.getDueDate();
                lends.setDueDate(currentDueDate.plusDays(daysToExtend));
                System.out.println("New due date is " + lends.getDueDate());
                return;
            }
        }

        System.out.println("Book series was not being lent. Please lend the series first before extending the period");
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
            lends.returnBook();
            lendings.remove(lends);
            System.out.println("Book " + book.getTitle() + " returned by " + user.getName() + ".");
            return;
        }

        System.out.println("Book " + book.getTitle() + " was not registered as being lent to " + user.getName());
    }

    /**
     * Returns a book from a user.
     *
     * @param user       The user returning the book.
     * @param bookSeries The book series being returned.
     * @throws UserOrBookDoesNotExistException If the user or the book is not found in the system.
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
