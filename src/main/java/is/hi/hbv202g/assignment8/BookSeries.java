package is.hi.hbv202g.assignment8;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a series of books authored by one or more authors.
 * A series must contain at least two books.
 * Implements the {@link ReadingMaterial} interface.
 */
public class BookSeries implements ReadingMaterial {

    private String title;
    private final List<Book> books;
    private final List<Author> authors;

    /**
     * Constructs a new {@code BookSeries} with a single author name.
     *
     * @param title      the title of the book series
     * @param books      the list of books in the series (must contain 2 or more)
     * @param authorName the name of the author of the series
     * @throws BookSeriesNotASeriesException if the number of books is less than 2
     */
    public BookSeries(String title, List<Book> books, String authorName)
            throws BookSeriesNotASeriesException {
        this.authors = new ArrayList<>();

        if (books.size() < 2) {
            throw new BookSeriesNotASeriesException("A book series requires more than one book");
        }

        this.title = title;
        this.books = books;
        this.authors.add(new Author(authorName));

        putBooksIntoSeries();
    }

    /**
     * Constructs a new {@code BookSeries} with a list of authors.
     *
     * @param title   the title of the book series
     * @param books   the list of books in the series (must contain 2 or more)
     * @param authors a non-empty list of authors
     * @throws BookSeriesNotASeriesException if the number of books is less than 2
     * @throws EmptyAuthorListException      if the author list is empty
     */
    public BookSeries(String title, List<Book> books, List<Author> authors)
            throws BookSeriesNotASeriesException, EmptyAuthorListException {

        if (books.size() < 2) {
            throw new BookSeriesNotASeriesException("A book series requires more than one book");
        }

        if (authors.isEmpty()) {
            throw new EmptyAuthorListException("Author list cannot be empty");
        }

        this.title = title;
        this.books = books;
        this.authors = authors;

        putBooksIntoSeries();
    }

    /**
     * Marks all books in the series as being part of a series.
     * This method is called internally by the constructor.
     */
    void putBooksIntoSeries() {
        for (Book book : this.books) {
            book.setInSeries(true);
        }
    }

    /**
     * Borrows all books in the series if they are all currently available.
     * Books become unavailable after borrowing.
     */
    public void borrowSeries() {
        if (isAvailable()) {
            for (Book book : books) {
                book.borrowBook();
            }
        }
    }

    /**
     * Returns all books in the series, making them available again.
     */
    public void returnSeries() {
        for (Book book : books) {
            book.returnBook();
        }
    }

    /**
     * Returns the title of the book series.
     *
     * @return the title as a {@code String}
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the book series.
     *
     * @param title the new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the list of authors of the book series.
     *
     * @return a list of {@link Author} objects
     */
    @Override
    public List<Author> getAuthors() {
        return authors;
    }

    /**
     * Returns the list of books in the series.
     *
     * @return a list of {@link Book} objects
     */
    public List<Book> getBooks() {
        return books;
    }

    /**
     * Checks whether all books in the series are available.
     *
     * @return {@code true} if all books are available, {@code false} otherwise
     */
    @Override
    public boolean isAvailable() {
        for (Book book : books) {
            if (!book.isAvailable()) {
                return false;
            }
        }
        return true;
    }
}
