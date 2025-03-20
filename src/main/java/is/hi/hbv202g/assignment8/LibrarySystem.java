package is.hi.hbv202g.assignment8;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibrarySystem {
    private final List<User> users = new ArrayList<>();
    private final List<Lending> lendings = new ArrayList<>();
    private final List<Book> books = new ArrayList<>();

    public LibrarySystem() {
    }

    public void addBookWithTitleAndNameOfSingleAuthor(String title, String authorName) {
        books.add(new Book(title, authorName));
    }

    public void addBookWithTitleAndAuthorList(String title, List<Author> authors) throws EmptyAuthorListException {
        if (authors == null || authors.isEmpty()) {
            throw new EmptyAuthorListException("Author list cannot be empty");
        }

        books.add(new Book(title, authors));
    }

    public void addStudentUser(String name, boolean feePaid) {
        users.add(new Student(name, feePaid));
    }

    public void addFacultyMemberUser(String name, String department) {
        users.add(new FacultyMember(name, department));
    }

    public Book findBookByTitle(String title) {
        return null;
    }

    public User findUserByName(String name) {
        return null;
    }

    public void borrowBook(User user, Book book) throws UserOrBookDoesNotExistException {
        if (!users.contains(user)) {
            throw new UserOrBookDoesNotExistException("User could not be found");
        }

        if (!books.contains(book)) {
            throw new UserOrBookDoesNotExistException("Book could not be found");
        }
    }

    public void extendLending(FacultyMember facultyMember, Book book, LocalDate newDueDate) {

    }

    public void returnBook(User user, Book book) throws UserPrincipalNotFoundException {

    }


}
