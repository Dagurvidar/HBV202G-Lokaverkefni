package is.hi.hbv202g.assignment8;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibrarySystem {
    private final List<User> users = new ArrayList<>();

    public LibrarySystem() {
    }

    public void addBookWithTitleAndNameOfSingleAuthor(String title, String authorName) {

    }

    public void addBookWithTitleAndAuthorList(String title, List<Author> authors) throws EmptyAuthorListException {

    }

    public void addStudentUser(String name, boolean feePaid) {
        if (feePaid) {
            users.add(new User(name));
        }
    }

    public void addFacultyMemberUser(String name, String department) {

    }

    public Book findBookByTitle(String title) {
        return null;
    }

    public User findUserByName(String name) {
        return null;
    }

    public void borrowBook(User user, Book book) throws UserOrBookDoesNotExistException {

    }

    public void extendLending(FacultyMember facultyMember, Book book, LocalDate newDueDate) {

    }

    public void returnBook(User user, Book book) throws UserPrincipalNotFoundException {

    }


}
