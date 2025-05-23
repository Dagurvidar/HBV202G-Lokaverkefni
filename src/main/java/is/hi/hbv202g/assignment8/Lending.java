package is.hi.hbv202g.assignment8;

import java.time.LocalDate;

/**
 * Represents a lending of a book or book series to a user with an associated due date.
 */
public class Lending {
    private LocalDate dueDate;
    private Book book;
    private BookSeries bookSeries;
    private User user;

    /**
     * Constructs a Lending object for a single book and user.
     * The due date is set to 30 days from the current date.
     * The book is marked as borrowed.
     *
     * @param book the book being borrowed
     * @param user the user borrowing the book
     */
    public Lending(Book book, User user) {
        this.dueDate = LocalDate.now().plusDays(30);
        this.book = book;
        this.user = user;

        book.borrowBook();
    }

    /**
     * Constructs a Lending object for a book series and user.
     * The due date is set to 30 days from the current date.
     * All books in the series are marked as borrowed.
     *
     * @param bookSeries the book series being borrowed
     * @param user the user borrowing the book series
     */
    public Lending(BookSeries bookSeries, User user) {
        this.dueDate = LocalDate.now().plusDays(30);
        this.bookSeries = bookSeries;
        this.user = user;

        bookSeries.borrowSeries();
    }

    /**
     * Marks the single book in this lending as returned.
     */
    public void returnBook() {
        book.returnBook();
    }

    /**
     * Marks all books in the book series as returned.
     */
    public void returnBookSeries() {
        bookSeries.returnSeries();
    }

    /**
     * Returns the due date of the lending.
     *
     * @return the due date
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Sets a new due date for the lending.
     *
     * @param dueDate the new due date
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Returns the single book associated with this lending.
     *
     * @return the book being lent, or null if this is a series lending
     */
    public Book getBook() {
        return book;
    }

    /**
     * Sets the book associated with this lending.
     *
     * @param book the book to associate
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * Returns the book series associated with this lending.
     *
     * @return the book series being lent, or null if this is a single book lending
     */
    public BookSeries getBookSeries() {
        return bookSeries;
    }

    /**
     * Sets the book series associated with this lending.
     *
     * @param bookSeries the book series to associate
     */
    public void setBookSeries(BookSeries bookSeries) {
        this.bookSeries = bookSeries;
    }

    /**
     * Returns the user associated with this lending.
     *
     * @return the user who borrowed the book or series
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user associated with this lending.
     *
     * @param user the user to associate
     */
    public void setUser(User user) {
        this.user = user;
    }
}
