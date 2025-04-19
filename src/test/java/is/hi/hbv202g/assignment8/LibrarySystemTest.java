package is.hi.hbv202g.assignment8;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LibrarySystemTest {

    private LibrarySystem system;
    private Book book;
    private FacultyMember faculty;
    private Student student;

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

    @Test
    public void testBorrowBookSuccess() throws UserOrBookDoesNotExistException {
        system.borrowBook(student, book);
        assertFalse(book.isAvailable());
    }

    @Test
    public void testReturnBookSuccess() throws UserOrBookDoesNotExistException {
        system.borrowBook(student, book);
        system.returnBook(student, book);
        assertTrue(book.isAvailable());
    }

    @Test
    public void testExtendLendingByFaculty() throws UserOrBookDoesNotExistException {
        system.borrowBook(faculty, book);
        system.extendLendingOfSingleBook(faculty, book, 15);
        // No exception or failure = success
    }

    @Test(expected = UserOrBookDoesNotExistException.class)
    public void testBorrowBookWithNonExistentBookThrowsException() throws UserOrBookDoesNotExistException {
        Book fakeBook = new Book("Fake", "Nobody");
        system.borrowBook(student, fakeBook);
    }

    @Test(expected = UserOrBookDoesNotExistException.class)
    public void testFindMissingBookThrowsException() throws UserOrBookDoesNotExistException {
        system.findBookByTitle("Missing Book");
    }

    @Test(expected = UserOrBookDoesNotExistException.class)
    public void testFindMissingUserThrowsException() throws UserOrBookDoesNotExistException {
        system.findUserByName("Ghost");
    }

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
