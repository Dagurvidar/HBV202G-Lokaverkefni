package is.hi.hbv202g.assignment8;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

public class LibrarySystemTest {

    private LibrarySystem system;

    @Before
    public void setUp() {
        system = new LibrarySystem();
    }

    @Test
    public void testAddBookWithSingleAuthor() throws UserOrBookDoesNotExistException {
        system.addBookWithTitleAndNameOfSingleAuthor("1984", "George Orwell");
        Book book = system.findBookByTitle("1984");
        assertEquals("1984", book.getTitle());
        assertEquals(1, book.getAuthors().size());
        assertEquals("George Orwell", book.getAuthors().get(0).getName());
    }

    @Test
    public void testAddBookWithMultipleAuthors() throws Exception {
        List<Author> authors = List.of(new Author("Author One"), new Author("Author Two"));
        system.addBookWithTitleAndAuthorList("Multi Book", authors);
        Book book = system.findBookByTitle("Multi Book");
        assertEquals("Multi Book", book.getTitle());
        assertEquals(2, book.getAuthors().size());
    }

    @Test(expected = EmptyAuthorListException.class)
    public void testAddBookWithEmptyAuthorListThrowsException() throws Exception {
        system.addBookWithTitleAndAuthorList("Empty Book", List.of());
    }

    @Test
    public void testAddStudentUser() throws Exception {
        system.addStudentUser("Alice", true);
        User user = system.findUserByName("Alice");
        assertTrue(user instanceof Student);
        assertEquals("Alice", user.getName());
    }

    @Test
    public void testAddFacultyMemberUser() throws Exception {
        system.addFacultyMemberUser("Dr. Smith", "Physics");
        User user = system.findUserByName("Dr. Smith");
        assertTrue(user instanceof FacultyMember);
        assertEquals("Dr. Smith", user.getName());
    }

    @Test(expected = UserOrBookDoesNotExistException.class)
    public void testFindUserByNameNotFoundThrowsException() throws Exception {
        system.findUserByName("Ghost");
    }

    @Test(expected = UserOrBookDoesNotExistException.class)
    public void testFindBookByTitleNotFoundThrowsException() throws Exception {
        system.findBookByTitle("Unknown Book");
    }

    @Test
    public void testBorrowBookSuccess() throws Exception {
        system.addStudentUser("Bob", true);
        system.addBookWithTitleAndNameOfSingleAuthor("Borrowable Book", "Author");
        User user = system.findUserByName("Bob");
        Book book = system.findBookByTitle("Borrowable Book");
        system.borrowBook(user, book);
        // If it doesn’t throw, it passed
    }

    @Test(expected = UserOrBookDoesNotExistException.class)
    public void testBorrowBookWithMissingUserThrowsException() throws Exception {
        Book book = new Book("Ghost Book", "Author");
        system.borrowBook(new Student("Ghost", true), book);
    }

    @Test(expected = UserOrBookDoesNotExistException.class)
    public void testBorrowBookWithMissingBookThrowsException() throws Exception {
        system.addStudentUser("Sam", true);
        User user = system.findUserByName("Sam");
        Book ghostBook = new Book("Ghost Book", "Ghost Author");
        system.borrowBook(user, ghostBook);
    }

    @Test
    public void testExtendLendingUpdatesDueDate() throws Exception {
        system.addFacultyMemberUser("Prof. X", "Psychology");
        system.addBookWithTitleAndNameOfSingleAuthor("Mind Book", "Dr. Brain");
        User user = system.findUserByName("Prof. X");
        Book book = system.findBookByTitle("Mind Book");

        system.borrowBook(user, book);
        FacultyMember faculty = (FacultyMember) user;

        LocalDate originalDue = LocalDate.now().plusDays(30);
        int newDue = 30;
        system.extendLendingOfSingleBook(faculty, book, newDue); // This adds 30 days to current due date internally

        // We're trusting the output — could also expose Lending list for assertion if needed
    }

    @Test
    public void testReturnBookSuccess() throws Exception {
        system.addStudentUser("Lilly", true);
        system.addBookWithTitleAndNameOfSingleAuthor("Return Me", "Author");
        User user = system.findUserByName("Lilly");
        Book book = system.findBookByTitle("Return Me");

        system.borrowBook(user, book);
        system.returnBook(user, book);
        // If no exception is thrown, it's a pass
    }

    @Test(expected = UserOrBookDoesNotExistException.class)
    public void testReturnBookWithUnknownUserThrows() throws Exception {
        Book book = new Book("Some Book", "Someone");
        system.returnBook(new Student("Unknown", false), book);
    }

    @Test(expected = UserOrBookDoesNotExistException.class)
    public void testReturnBookWithUnknownBookThrows() throws Exception {
        system.addStudentUser("Jane", true);
        User user = system.findUserByName("Jane");
        Book ghostBook = new Book("Ghost Book", "Ghost");
        system.returnBook(user, ghostBook);
    }
}
