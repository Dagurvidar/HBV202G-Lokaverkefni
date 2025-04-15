package is.hi.hbv202g.assignment8;

import java.util.ArrayList;
import java.util.List;

public class Book implements ReadingMaterial {
    private boolean isAvailable;
    private String title;
    private List<Author> authors = new ArrayList<>();

    public Book(String title, String authorName) {
        this.title = title;
        this.authors.add(new Author(authorName));
    }

    public Book(String title, List<Author> authors) throws EmptyAuthorListException {
        if (authors == null || authors.isEmpty()) {
            throw new EmptyAuthorListException("Author list cannot be empty");
        }

        this.title = title;
        this.authors = authors;
    }

    public void lendOut() {
        isAvailable = false;
    }

    public void returnBook() {
        isAvailable = true;
    }

    public void addAuthor(Author author) {
        this.authors.add(author);
    }

    @Override
    public List<Author> getAuthors() {
        return authors;
    }

    @Override
    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAuthors(List<Author> authors) throws EmptyAuthorListException {
        if (authors == null || authors.isEmpty()) {
            throw new EmptyAuthorListException("Author list cannot be empty");
        }
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
