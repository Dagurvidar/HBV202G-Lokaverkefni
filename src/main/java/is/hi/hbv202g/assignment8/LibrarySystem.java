package is.hi.hbv202g.assignment8;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibrarySystem {
    private final List<User> users = new ArrayList<>();
    private final List<Lending> lendings = new ArrayList<>();
    private final List<Book> books = new ArrayList<>();
    private final List<BookSeries> bookSeriesList = new ArrayList<>();

    public LibrarySystem() {
    }

    public void listAllBooks() {
        if (books.isEmpty() && bookSeriesList.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            System.out.println("\n--- All Books in Library ---");
            for (Book book : books) {
                System.out.println("- " + book.getTitle() + ", by " + book.getAuthors());
            }
            for (BookSeries series : bookSeriesList) {
                System.out.println("- " + series.getTitle() + ", by " + series.getAuthors());
            }
        }
    }

    /**
     * @param title      of book
     * @param authorName name of author
     */
    public void addBookWithTitleAndNameOfSingleAuthor(String title, String authorName) {
        books.add(new Book(title, authorName));
    }

    /**
     * @param title   of book
     * @param authors of book
     * @throws EmptyAuthorListException author list should not be empty
     */
    public void addBookWithTitleAndAuthorList(String title, List<Author> authors) throws EmptyAuthorListException {
        books.add(new Book(title, authors));
    }

    /**
     * Books added by this method are also added to the library as individual books.
     * It is assumed in this method that every book in a series has the same author(s).
     * @param seriesTitle title of book series
     * @param books list of books in series
     * @param authors of books in series
     * @throws EmptyAuthorListException author list should not be empty
     */
    public void addBookSeries(String seriesTitle, List<Book> books, List<Author> authors) throws EmptyAuthorListException, BookSeriesNotASeriesException {
        BookSeries series = new BookSeries(seriesTitle, books, authors);
        bookSeriesList.add(series);
        books.addAll(series.getBooks());
    }

    /**
     * @param name    of student
     * @param feePaid has the lending fee been paid
     */
    public void addStudentUser(String name, boolean feePaid) {
        users.add(new Student(name, feePaid));
    }

    /**
     * @param name       of staff member
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

        if (book.isAvailable()) {
            Lending lending = new Lending(book, user);
            lendings.add(lending);
            System.out.println(book.getTitle() + " lent to " + user.getName() + "successfully!");
        } else {
            System.out.println("Book is already being loaned");
        }
    }

    /**
     * @param facultyMember in charge of extending the lending period
     * @param book          that has been lent
     * @param newDueDate    new date of extended lending time
     * @throws UserOrBookDoesNotExistException if the book is not found in the system
     */
    public void extendLending(FacultyMember facultyMember, Book book, int daysToExtend) throws UserOrBookDoesNotExistException {
        if (!books.contains(book)) {
            throw new UserOrBookDoesNotExistException("Book could not be found");
        }

        if (!users.contains(facultyMember)) {
            System.out.println("Faculty member not found, lending failed");
            return;
        }

        for (Lending lends : lendings) {
            if (lends.getBook().equals(book)) {
                LocalDate currentDueDate = lends.getDueDate();
                lends.setDueDate(currentDueDate.plusDays(daysToExtend));
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
            lends.returnBook();
            lendings.remove(lends);
            System.out.println("Book" + book.getTitle() + "returned by " + user.getName() + ".");
            return;
        }

        System.out.println("Book " + book.getTitle() + " was not registered as being lent to " + user.getName());
    }
}
