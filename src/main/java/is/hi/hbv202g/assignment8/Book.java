package is.hi.hbv202g.assignment8;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a book in the library system, which may have one or more authors.
 */
public class Book implements ReadingMaterial {
    private boolean isAvailable;
    private String title;
    private List<Author> authors = new ArrayList<>();

    /**
     * Constructs a book with a given title and a single author.
     *
     * @param title      the title of the book
     * @param authorName the name of the author
     */
    public Book(String title, String authorName) {
        this.title = title;
        this.authors.add(new Author(authorName));
    }

    /**
     * Constructs a book with a given title and a list of authors.
     *
     * @param title   the title of the book
     * @param authors the list of authors
     * @throws EmptyAuthorListException if the list of authors is null or empty
     */
    public Book(String title, List<Author> authors) throws EmptyAuthorListException {
        if (authors == null || authors.isEmpty()) {
            throw new EmptyAuthorListException("Author list cannot be empty");
        }

        this.title = title;
        this.authors = authors;
    }

    public void borrowBook() {
        isAvailable = false;
    }

    public void returnBook() {
        isAvailable = true;
    }

    /**
     * Adds an author to the book's list of authors.
     *
     * @param author the author to add
     */
    public void addAuthor(Author author) {
        this.authors.add(author);
    }

    /**
     * Returns the list of authors for this book.
     *
     * @return a list of authors
     */
    @Override
    public List<Author> getAuthors() {
        return authors;
    }

    /**
     * Sets the list of authors for this book.
     *
     * @param authors the new list of authors
     * @throws EmptyAuthorListException if the list of authors is null or empty
     */
    public void setAuthors(List<Author> authors) throws EmptyAuthorListException {
        if (authors == null || authors.isEmpty()) {
            throw new EmptyAuthorListException("Author list cannot be empty");
        }
        this.authors = authors;
    }

    @Override
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * Returns the title of the book.
     *
     * @return the book title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the book.
     *
     * @param title the new book title
     */
    public void setTitle(String title) {
        this.title = title;
    }

}
