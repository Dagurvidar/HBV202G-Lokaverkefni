package is.hi.hbv202g.assignment8;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

/**
 * Unit tests for the {@link Lending} class.
 */
public class LendingTest {
    private User user;
    private Book book;
    private Lending lending;

    /**
     * Sets up test data before each test case.
     */
    @Before
    public void setUp() {
        user = new Student("Alice", true);
        book = new Book("Effective Java", "Joshua Bloch");
        lending = new Lending(book, user);
    }

    /**
     * Tests that the due date is set correctly to 30 days from now when a Lending is created.
     */
    @Test
    public void testLendingDueDate() {
        LocalDate expectedDueDate = LocalDate.now().plusDays(30);
        assertEquals(expectedDueDate, lending.getDueDate());
    }

    /**
     * Tests that the due date can be updated correctly.
     */
    @Test
    public void testSetDueDate() {
        LocalDate newDueDate = LocalDate.now().plusDays(60);
        lending.setDueDate(newDueDate);
        assertEquals(newDueDate, lending.getDueDate());
    }

    /**
     * Tests that the Lending object correctly stores and returns the associated book and user.
     */
    @Test
    public void testLendingDetails() {
        assertEquals(book, lending.getBook());
        assertEquals(user, lending.getUser());
    }
}
