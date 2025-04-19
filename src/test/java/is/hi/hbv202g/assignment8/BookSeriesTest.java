package is.hi.hbv202g.assignment8;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookSeriesTest {
    private Book book1;
    private Book book2;
    private Book book3;
    private List<Book> books;
    private BookSeries bookSeries;
    private Author author;

    @Before
    public void setUp() throws Exception {
        book1 = new Book("Book One", "Author A");
        book2 = new Book("Book Two", "Author A");
        book3 = new Book("Book Three", "Author A");
        books = new ArrayList<>(Arrays.asList(book1, book2, book3));
        author = new Author("Author A");

        List<Author> authors = new ArrayList<>();
        authors.add(author);

        bookSeries = new BookSeries("Awesome Series", books, authors);
    }

    @Test
    public void testBookSeriesIsAvailableInitially() {
        assertTrue(bookSeries.isAvailable());
    }

    @Test
    public void testBorrowSeriesMakesBooksUnavailable() {
        bookSeries.borrowSeries();
        assertFalse(book1.isAvailable());
        assertFalse(book2.isAvailable());
        assertFalse(book3.isAvailable());
    }

    @Test
    public void testReturnSeriesMakesBooksAvailableAgain() {
        bookSeries.borrowSeries();
        bookSeries.returnSeries();
        assertTrue(book1.isAvailable());
        assertTrue(book2.isAvailable());
        assertTrue(book3.isAvailable());
    }

    @Test
    public void testGetTitle() {
        assertEquals("Awesome Series", bookSeries.getTitle());
    }

    @Test
    public void testSetTitle() {
        bookSeries.setTitle("New Title");
        assertEquals("New Title", bookSeries.getTitle());
    }

    @Test
    public void testGetAuthors() {
        List<Author> authors = bookSeries.getAuthors();
        assertEquals(1, authors.size());
        assertEquals("Author A", authors.get(0).getName());
    }

    @Test(expected = BookSeriesNotASeriesException.class)
    public void testConstructorWithOneBookThrowsException() throws Exception {
        List<Book> singleBookList = new ArrayList<>();
        singleBookList.add(new Book("Lonely Book", "Author A"));
        new BookSeries("Invalid Series", singleBookList, "Author A");
    }

    @Test(expected = EmptyAuthorListException.class)
    public void testConstructorWithEmptyAuthorListThrowsException() throws Exception {
        List<Author> emptyAuthors = new ArrayList<>();
        new BookSeries("Another Series", books, emptyAuthors);
    }
}
