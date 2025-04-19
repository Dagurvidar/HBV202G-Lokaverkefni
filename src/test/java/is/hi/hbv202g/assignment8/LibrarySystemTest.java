package is.hi.hbv202g.assignment8;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit tests for the {@link LibrarySystem} class.
 *
 * This class tests functionalities such as borrowing and returning books,
 * extending lending periods, and handling errors related to missing users or books.
 */
public class LibrarySystemTest {

    private LibrarySystem system;
    private Book book;
    private FacultyMember faculty;
    private Student student;

    /**
     * Sets up the library system with one student, one faculty member, and one book.
     * Initializes references for reuse in tests.
     */
    @Before
    public void setUp() {
        system = new LibrarySystem();
        system.addFacultyMemberUser("Alice", "Science");
        system.addStudentUser("Bob", true);
        system.addBookWithTitleAndNameOfSingleAuthor("Test Book", "Author A");

        try {
            book = system.findBookByTitle("Test Book");
            faculty = (FacultyMember) system.findUserByName("Alice");
            student = (Student) system.findUserByName("Bob");
        } catch (UserOrBookDoesNotExistException e) {
            fail("Setup failed: " + e.getMessage());
        }
    }

    /**
     * Tests successful borrowing of a book by a student.
     */
    @Test
    public void testBorrowBookSuccess() throws UserOrBookDoesNotExistException {
        system.borrowBook(student, book);
        assertFalse(book.isAvailable());
    }

    /**
     * Tests successful return of a borrowed book.
     */
    @Test
    public void testReturnBookSuccess() throws UserOrBookDoesNotExistException {
        system.borrowBook(student, book);
        system.returnBook(student, book);
        assertTrue(book.isAvailable());
    }

    /**
     * Tests that a faculty member can successfully extend the lending period of a borrowed book.
     */
    @Test
    public void testExtendLendingByFaculty() throws UserOrBookDoesNotExistException {
        system.borrowBook(faculty, book);
        system.extendLendingOfSingleBook(faculty, book, 15);
        // No exception means the operation was successful
    }

    /**
     * Tests that borrowing a non-existent book throws a UserOrBookDoesNotExistException.
     */
    @Test(expected = UserOrBookDoesNotExistException.class)
    public void testBorrowBookWithNonExistentBookThrowsException() throws UserOrBookDoesNotExistException {
        Book fakeBook = new Book("Fake", "Nobody");
        system.borrowBook(student, fakeBook);
    }

    /**
     * Tests that searching for a missing book by title throws a UserOrBookDoesNotExistException.
     */
    @Test(expected = UserOrBookDoesNotExistException.class)
    public void testFindMissingBookThrowsException() throws UserOrBookDoesNotExistException {
        system.findBookByTitle("Missing Book");
    }

    /**
     * Tests that searching for a non-existent user by name throws a UserOrBookDoesNotExistException.
     */
    @Test(expected = UserOrBookDoesNotExistException.class)
    public void testFindMissingUserThrowsException() throws UserOrBookDoesNotExistException {
        system.findUserByName("Ghost");
    }

    /**
     * Tests that a book can be added with multiple authors and correctly stored in the system.
     */
    @Test
    public void testAddBookWithMultipleAuthors() throws EmptyAuthorListException, UserOrBookDoesNotExistException {
        List<Author> authors = new ArrayList<>();
        authors.add(new Author("Author One"));
        authors.add(new Author("Author Two"));
        system.addBookWithTitleAndAuthorList("Multi Author Book", authors);
        Book multiBook = system.findBookByTitle("Multi Author Book");
        assertEquals(2, multiBook.getAuthors().size());
    }
}
