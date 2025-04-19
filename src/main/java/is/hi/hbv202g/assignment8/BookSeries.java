package is.hi.hbv202g.assignment8;

import java.util.List;

/**
 * Represents a series of books authored by one or more authors.
 * Implements the ReadingMaterial interface.
 */
public class BookSeries implements ReadingMaterial {
    private String title;
    private final List<Book> books;
    private final List<Author> authors;

    /**
     * Constructs a BookSeries object.
     *
     * @param title   the title of the series
     * @param books   the list of books in the series
     * @param authors the list of authors
     * @throws BookSeriesNotASeriesException if there are fewer than 2 books
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
    }

    /**
     * Returns the title of the book series.
     *
     * @return the title of the series
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
     * @return a list of authors
     */
    @Override
    public List<Author> getAuthors() {
        return authors;
    }

    /**
     * Returns the list of books in the series.
     *
     * @return a list of books
     */
    public List<Book> getBooks() {
        return books;
    }

    /**
     * Checks if all books in the series are available.
     *
     * @return true if all books are available, false otherwise
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
