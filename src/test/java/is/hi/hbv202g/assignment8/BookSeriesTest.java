package is.hi.hbv202g.assignment8;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class BookSeriesTest {
    private BookSeries lotrSeries;
    private Book book1;
    private Book book2;
    private Author tolkien;

    @Before
    public void setUp() {
        tolkien = new Author("J.R.R. Tolkien");

        // Create books for the test, ensuring no empty author lists
        try {
            book1 = new Book("The Fellowship of the Ring", List.of(tolkien)); // Ensure at least one author
            book2 = new Book("The Two Towers", List.of(tolkien));            // Ensure at least one author

            // Create BookSeries for testing with more than one book
            List<Book> lotrBooks = List.of(book1, book2);
            lotrSeries = new BookSeries("The Lord of the Rings", lotrBooks, List.of(tolkien));
        } catch (EmptyAuthorListException | BookSeriesNotASeriesException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBookSeriesTitle() {
        assertEquals("The Lord of the Rings", lotrSeries.getTitle());
    }

    @Test
    public void testBookSeriesAuthors() {
        assertEquals("J.R.R. Tolkien", lotrSeries.getAuthors().get(0).getName());
    }

    @Test
    public void testBookSeriesAvailabilityWhenAllBooksAreAvailable() {
        assertTrue(lotrSeries.isAvailable());  // Assuming both books are available
    }

    @Test
    public void testBookSeriesAvailabilityWhenOneBookIsUnavailable() {
        // Making book1 unavailable
        book1.lendOut();

        // Now the series should be unavailable
        assertFalse(lotrSeries.isAvailable());
    }

    @Test
    public void testCreateBookSeriesWithLessThanTwoBooksThrowsException() {
        try {
            // Creating a BookSeries with only one book, which should throw an exception
            BookSeries singleBookSeries = new BookSeries("Single Book Series", List.of(book1), List.of(tolkien));
            fail("Expected BookSeriesNotASeriesException to be thrown");
        } catch (BookSeriesNotASeriesException e) {
            assertEquals("A book series requires more than one book", e.getMessage());
        } catch (EmptyAuthorListException e) {
            fail("Expected BookSeriesNotASeriesException, but got EmptyAuthorListException");
        }
    }

    @Test
    public void testCreateBookSeriesWithEmptyAuthorListThrowsException() {
        try {
            // Creating a BookSeries with no authors, should throw exception
            List<Book> booksWithoutAuthors = List.of(new Book("No Author Book", List.of()));  // Ensure non-empty author list here
            new BookSeries("Invalid Book Series", booksWithoutAuthors, List.of()); // Empty authors list here is fine as exception
            fail("Expected EmptyAuthorListException to be thrown");
        } catch (EmptyAuthorListException e) {
            assertEquals("Author list cannot be empty", e.getMessage());
        } catch (BookSeriesNotASeriesException e) {
            fail("Expected EmptyAuthorListException, but got BookSeriesNotASeriesException");
        }
    }

    @Test
    public void testBookAvailabilityAfterReturningBook() {
        // Making book1 unavailable (lent out)
        book1.lendOut();
        assertFalse(book1.isAvailable());

        // Returning book1
        book1.returnBook();
        assertTrue(book1.isAvailable());
    }

    @Test(expected = EmptyAuthorListException.class)
    public void testBookWithEmptyAuthorListThrowsException() throws EmptyAuthorListException {
        // Creating a Book with an empty author list should throw an exception
        new Book("No Author Book", List.of());
    }
}
