package is.hi.hbv202g.assignment8;

import java.time.LocalDate;

/**
 * Represents a lending of a book to a user with a due date.
 */
public class Lending {
    private LocalDate dueDate;
    private Book book;
    private User user;

    /**
     * Creates a new Lending object with the given book and user.
     * The due date is set to 30 days from the current date.
     *
     * @param book The book being lent.
     * @param user The user who is borrowing the book.
     */
    public Lending(Book book, User user) {
        this.dueDate = LocalDate.now().plusDays(30);
        this.book = book;
        this.user = user;

        book.lendOut();
    }

    public void returnBook() {
        book.returnBook();
    }

    /**
     * Gets the due date for the lending.
     *
     * @return The due date of the lending.
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Sets a new due date for the lending.
     *
     * @param dueDate The new due date for the lending.
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Gets the book that has been lent.
     *
     * @return The book being lent.
     */
    public Book getBook() {
        return book;
    }

    /**
     * Sets a new book for the lending.
     *
     * @param book The new book to be lent.
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * Gets the user who has borrowed the book.
     *
     * @return The user who has borrowed the book.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets a new user who is borrowing the book.
     *
     * @param user The new user borrowing the book.
     */
    public void setUser(User user) {
        this.user = user;
    }
}
