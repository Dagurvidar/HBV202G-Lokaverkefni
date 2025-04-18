package is.hi.hbv202g.assignment8;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class LendingTest {
    private User user;
    private Book book;
    private Lending lending;

    @Before
    public void setUp() {
        user = new Student("Alice", true);
        book = new Book("Effective Java", "Joshua Bloch");
        lending = new Lending(book, user);
    }

    @Test
    public void testLendingDueDate() {
        LocalDate expectedDueDate = LocalDate.now().plusDays(30);
        assertEquals(expectedDueDate, lending.getDueDate());
    }

    @Test
    public void testSetDueDate() {
        LocalDate newDueDate = LocalDate.now().plusDays(60);
        lending.setDueDate(newDueDate);
        assertEquals(newDueDate, lending.getDueDate());
    }

    @Test
    public void testLendingDetails() {
        assertEquals(book, lending.getBook());
        assertEquals(user, lending.getUser());
    }
}
