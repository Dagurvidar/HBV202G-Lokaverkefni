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

    /**
     * @param title of book
     * @param authorName name of author
     */
    public void addBookWithTitleAndNameOfSingleAuthor(String title, String authorName) {
        books.add(new Book(title, authorName));
    }

    /**
     * @param title of book
     * @param authors of book
     * @throws EmptyAuthorListException author list should not be empty
     */
    public void addBookWithTitleAndAuthorList(String title, List<Author> authors) throws EmptyAuthorListException {
        if (authors == null || authors.isEmpty()) {
            throw new EmptyAuthorListException("Author list cannot be empty");
        }

        books.add(new Book(title, authors));
    }

    /**
     * @param name of student
     * @param feePaid has the lending fee been paid
     */
    public void addStudentUser(String name, boolean feePaid) {
        users.add(new Student(name, feePaid));
    }

    /**
     * @param name of staff member
     * @param department of staff member
     */
    public void addFacultyMemberUser(String name, String department) {
        users.add(new FacultyMember(name, department));
    }

    /**
     * @param title of book
     * @return Book object
     * @throws UserOrBookDoesNotExistException if book is not found
     */
    public Book findBookByTitle(String title) throws UserOrBookDoesNotExistException {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }

        throw new UserOrBookDoesNotExistException("Book could not be found");
    }

    /**
     * @param name of user
     * @return User object
     * @throws UserOrBookDoesNotExistException if user is not found
     */
    public User findUserByName(String name) throws UserOrBookDoesNotExistException {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }

        throw new UserOrBookDoesNotExistException("User could not be found");
    }

    /**
     * @param user user who is borrowing the book
     * @param book the book that is being borrowed
     * @throws UserOrBookDoesNotExistException if user or book is not found in the system
     */
    public void borrowBook(User user, Book book) throws UserOrBookDoesNotExistException {
        if (!users.contains(user)) {
            throw new UserOrBookDoesNotExistException("User could not be found");
        }
        if (!books.contains(book)) {
            throw new UserOrBookDoesNotExistException("Book could not be found");
        }

        Lending lending = new Lending(book, user);
        lendings.add(lending);
        System.out.println(book.getTitle() + " lent to " + user.getName() + "successfully!");
    }

    /**
     * @param facultyMember in charge of extending the lending period
     * @param book that has been lent
     * @param newDueDate new date of extended lending time
     * @throws UserOrBookDoesNotExistException if the book is not found in the system
     */
    public void extendLending(FacultyMember facultyMember, Book book, LocalDate newDueDate) throws UserOrBookDoesNotExistException {
        if (!books.contains(book)) {
            throw new UserOrBookDoesNotExistException("Book could not be found");
        }

        for (Lending lends : lendings) {
            if (lends.getBook().equals(book)) {
                LocalDate currentDueDate = lends.getDueDate();
                lends.setDueDate(currentDueDate.plusDays(30));
                System.out.println("New due date is " + lends.getDueDate());
                return;
            }
        }

        System.out.println("Book was not being lent. Please lend the book first before extending lending period");
    }

    /**
     * @param user user who is returning the book
     * @param book the book that is being returned
     * @throws UserOrBookDoesNotExistException if book or user cannot be found in the system
     */
    public void returnBook(User user, Book book) throws UserOrBookDoesNotExistException {
        if (!users.contains(user)) {
            throw new UserOrBookDoesNotExistException("User could not be found");
        }

        if (!books.contains(book)) {
            throw new UserOrBookDoesNotExistException("Book could not be found");
        }

        for (Lending lends : lendings) {
            lendings.remove(lends);
            System.out.println("Book" + book.getTitle() + "returned by " + user.getName() + ".");
            return;
        }

        System.out.println("Book " + book.getTitle() + " was not registered as being lent to " + user.getName());
    }
}
