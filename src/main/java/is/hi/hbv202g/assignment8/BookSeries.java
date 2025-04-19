package is.hi.hbv202g.assignment8;

import java.util.List;

public class BookSeries implements ReadingMaterial {
    private String title;
    private final List<Book> books;
    private final List<Author> authors;

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

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public List<Author> getAuthors() {
        return authors;
    }

    public List<Book> getBooks() {
        return books;
    }

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
