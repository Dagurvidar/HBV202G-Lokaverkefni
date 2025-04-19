package is.hi.hbv202g.assignment8;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Unit tests for the {@link BookSeries} class.
 *
 * Tests various functionalities of a book series, including availability,
 * borrowing and returning behavior, title and author accessors, and
 * exception handling for invalid input.
 */
public class BookSeriesTest {

    private Book book1;
    private Book book2;
    private Book book3;
    private List<Book> books;
    private BookSeries bookSeries;
    private Author author;

    /**
     * Initializes test data before each test.
     * Creates a book series with three books by the same author.
     */
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

    /**
     * Verifies that a newly created book series is initially available.
     */
    @Test
    public void testBookSeriesIsAvailableInitially() {
        assertTrue(bookSeries.isAvailable());
    }

    /**
     * Tests that borrowing a book series makes all books in the series unavailable.
     */
    @Test
    public void testBorrowSeriesMakesBooksUnavailable() {
        bookSeries.borrowSeries();
        assertFalse(book1.isAvailable());
        assertFalse(book2.isAvailable());
        assertFalse(book3.isAvailable());
    }

    /**
     * Tests that returning a borrowed book series makes all books available again.
     */
    @Test
    public void testReturnSeriesMakesBooksAvailableAgain() {
        bookSeries.borrowSeries();
        bookSeries.returnSeries();
        assertTrue(book1.isAvailable());
        assertTrue(book2.isAvailable());
        assertTrue(book3.isAvailable());
    }

    /**
     * Tests that the title of the book series is correctly returned.
     */
    @Test
    public void testGetTitle() {
        assertEquals("Awesome Series", bookSeries.getTitle());
    }

    /**
     * Tests that the title of the book series can be updated and retrieved correctly.
     */
    @Test
    public void testSetTitle() {
        bookSeries.setTitle("New Title");
        assertEquals("New Title", bookSeries.getTitle());
    }

    /**
     * Verifies that the list of authors in the series contains the correct author.
     */
    @Test
    public void testGetAuthors() {
        List<Author> authors = bookSeries.getAuthors();
        assertEquals(1, authors.size());
        assertEquals("Author A", authors.get(0).getName());
    }

    /**
     * Tests that creating a book series with only one book throws a BookSeriesNotASeriesException.
     */
    @Test(expected = BookSeriesNotASeriesException.class)
    public void testConstructorWithOneBookThrowsException() throws Exception {
        List<Book> singleBookList = new ArrayList<>();
        singleBookList.add(new Book("Lonely Book", "Author A"));
        new BookSeries("Invalid Series", singleBookList, "Author A");
    }

    /**
     * Tests that creating a book series with an empty list of authors throws an EmptyAuthorListException.
     */
    @Test(expected = EmptyAuthorListException.class)
    public void testConstructorWithEmptyAuthorListThrowsException() throws Exception {
        List<Author> emptyAuthors = new ArrayList<>();
        new BookSeries("Another Series", books, emptyAuthors);
    }
}
