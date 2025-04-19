package is.hi.hbv202g.assignment8;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * The Main class serves as the entry point for interacting with the Library System.
 * It provides a command-line interface for managing books, users, and lendings.
 */
public class Main {

    /**
     * The main method starts the library system and displays the menu for user interactions.
     * It allows users to choose actions like adding books, borrowing and returning books,
     * and extending lending periods, while handling user input through a scanner.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        LibrarySystem system = new LibrarySystem();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        System.out.println("Filling library with books...");
        CreateBooks.fillLibrary(system);

        while (running) {
            System.out.println("\n--- Library System Menu ---");
            System.out.println("1. Add a book or book series");
            System.out.println("2. Add a user or faculty member");
            System.out.println("3. Borrow book or book series");
            System.out.println("4. Return book or book series");
            System.out.println("5. Extend lending");
            System.out.println("6. List all books");
            System.out.println("7. Quit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            // TODO breyta villu meðhöndlun þannig það grípi sérhverja EmptyAuthorListException o.s.frv. en ekki bara Exception
            // TODO Muna fyrir þetta^ að grípa villuna og nota e.getMessage til að fá rétt skilaboð.
            // TODO Þetta að neðan koma einhvern veginn
            /**
             * Choose an option: 5
             * Enter user's name: dagur
             * Enter book title: the two towers
             * Book is already being loaned
             * Book borrowed.
             */
            try {
                switch (choice) {
                    case 1:
                        System.out.print("Are you adding a (1) book or (2) book series? ");
                        int bookChoice = scanner.nextInt();
                        scanner.nextLine();

                        if (bookChoice == 1) {
                            System.out.print("Single author (1) or multiple authors (2)? ");
                            int authorCount = scanner.nextInt();
                            scanner.nextLine();

                            System.out.print("Enter book title: ");
                            String bookTitle = scanner.nextLine();

                            if (authorCount == 1) {
                                System.out.print("Enter author name: ");
                                String authorName = scanner.nextLine();
                                system.addBookWithTitleAndNameOfSingleAuthor(bookTitle, authorName);
                            } else {
                                List<Author> authors = new ArrayList<>();
                                while (true) {
                                    System.out.print("Enter author name (or 'done'): ");
                                    String name = scanner.nextLine();
                                    if (name.equalsIgnoreCase("done")) break;
                                    authors.add(new Author(name));
                                }
                                system.addBookWithTitleAndAuthorList(bookTitle, authors);
                            }
                            System.out.println("Book added.");

                        } else {
                            System.out.print("Single author (1) or multiple authors (2)? ");
                            int authorCount = scanner.nextInt();
                            scanner.nextLine();

                            System.out.print("Enter book series title: ");
                            String seriesTitle = scanner.nextLine();

                            List<Book> booksInSeries = new ArrayList<>();
                            if (authorCount == 1) {
                                System.out.print("Enter author name: ");
                                String authorName = scanner.nextLine();

                                while (true) {
                                    System.out.print("Enter book title (or 'done'): ");
                                    String title = scanner.nextLine();
                                    if (title.equalsIgnoreCase("done")) break;
                                    booksInSeries.add(new Book(title, authorName));
                                }
                                system.addBookSeriesWithTitleAndNameOfSingleAuthor(seriesTitle, booksInSeries, authorName);

                            } else {
                                List<Author> authors = new ArrayList<>();
                                while (true) {
                                    System.out.print("Enter author name (or 'done'): ");
                                    String name = scanner.nextLine();
                                    if (name.equalsIgnoreCase("done")) break;
                                    authors.add(new Author(name));
                                }

                                while (true) {
                                    System.out.print("Enter book title (or 'done'): ");
                                    String title = scanner.nextLine();
                                    if (title.equalsIgnoreCase("done")) break;
                                    booksInSeries.add(new Book(title, authors));
                                }
                                system.addBookSeriesWithTitleAndAuthorList(seriesTitle, booksInSeries, authors);
                            }
                            System.out.println("Book series added.");
                        }
                        break;

                    case 2:
                        System.out.print("Add (1) Student or (2) Faculty Member? ");
                        int userType = scanner.nextInt();
                        scanner.nextLine();

                        if (userType == 1) {
                            System.out.print("Enter student name: ");
                            String studentName = scanner.nextLine();
                            System.out.print("Fee paid? (true/false): ");
                            boolean feePaid = scanner.nextBoolean();
                            scanner.nextLine();
                            system.addStudentUser(studentName, feePaid);
                            System.out.println("Student added.");
                        } else {
                            System.out.print("Enter faculty name: ");
                            String facultyName = scanner.nextLine();
                            System.out.print("Enter department: ");
                            String department = scanner.nextLine();
                            system.addFacultyMemberUser(facultyName, department);
                            System.out.println("Faculty member added.");
                        }
                        break;

                    case 3:
                        System.out.print("Borrow (1) book or (2) book series? ");
                        int borrowType = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter user name: ");
                        String borrower = scanner.nextLine();
                        User borrowingUser = system.findUserByName(borrower);

                        if (borrowType == 1) {
                            System.out.print("Enter book title: ");
                            String bookTitle = scanner.nextLine();
                            Book book = system.findBookByTitle(bookTitle);
                            system.borrowBook(borrowingUser, book);
                        } else {
                            System.out.print("Enter series title: ");
                            String seriesTitle = scanner.nextLine();
                            BookSeries series = system.findBookSeriesByTitle(seriesTitle);
                            system.borrowBookSeries(borrowingUser, series);
                        }
                        break;

                    case 4:
                        System.out.print("Return (1) book or (2) book series? ");
                        int returnType = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter user name: ");
                        String returner = scanner.nextLine();
                        User returningUser = system.findUserByName(returner);

                        if (returnType == 1) {
                            System.out.print("Enter book title: ");
                            String bookTitle = scanner.nextLine();
                            Book book = system.findBookByTitle(bookTitle);
                            system.returnBook(returningUser, book);
                        } else {
                            System.out.print("Enter series title: ");
                            String seriesTitle = scanner.nextLine();
                            BookSeries series = system.findBookSeriesByTitle(seriesTitle);
                            system.returnBookSeries(returningUser, series);
                        }
                        break;

                    case 5:
                        System.out.print("Extend lending for (1) book or (2) book series? ");
                        int extendType = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter faculty name: ");
                        String name = scanner.nextLine();
                        FacultyMember faculty = (FacultyMember) system.findUserByName(name);
                        System.out.print("Enter number of days to extend: ");
                        int days = scanner.nextInt();
                        scanner.nextLine();

                        if (extendType == 1) {
                            System.out.print("Enter book title: ");
                            String bookTitle = scanner.nextLine();
                            Book book = system.findBookByTitle(bookTitle);
                            system.extendLendingOfSingleBook(faculty, book, days);
                        } else {
                            System.out.print("Enter series title: ");
                            String seriesTitle = scanner.nextLine();
                            BookSeries series = system.findBookSeriesByTitle(seriesTitle);
                            system.extendLendingOfBookSeries(faculty, series, days);
                        }
                        break;

                    case 6:
                        system.listAllBooks();
                        break;

                    case 7:
                        running = false;
                        System.out.println("Exiting system. Bye!");
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (Exception e) {
                // Handle errors gracefully
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();

    }

    private static void addBookSeriesWithSingleAuthor(LibrarySystem system, Scanner scanner) {
        try {
            System.out.print("Enter book series title: ");
            String seriesTitle = scanner.nextLine();

            System.out.print("Enter author name: ");
            String authorName = scanner.nextLine();

            List<Book> booksInSeries = new ArrayList<>();
            while (true) {
                System.out.print("Enter book title in series (or 'done' to finish): ");
                String bookTitle = scanner.nextLine();
                if (bookTitle.equalsIgnoreCase("done")) {
                    break;
                }
                booksInSeries.add(new Book(bookTitle, authorName));
            }

            system.addBookSeriesWithTitleAndNameOfSingleAuthor(seriesTitle, booksInSeries, authorName);
            System.out.println("Book series added.");
        } catch (EmptyAuthorListException | BookSeriesNotASeriesException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void addBookSeriesWithMultipleAuthors(LibrarySystem system, Scanner scanner) {
        try {
            System.out.print("Enter book series title: ");
            String seriesTitle = scanner.nextLine();

            List<Author> authors = new ArrayList<>();
            while (true) {
                System.out.print("Enter author name (or 'done' to finish): ");
                String authorName = scanner.nextLine();
                if (authorName.equalsIgnoreCase("done")) break;
                authors.add(new Author(authorName));
            }

            List<Book> booksInSeries = new ArrayList<>();
            while (true) {
                System.out.print("Enter book title in series (or 'done' to finish): ");
                String bookTitle = scanner.nextLine();
                if (bookTitle.equalsIgnoreCase("done")) break;
                booksInSeries.add(new Book(bookTitle, authors));
            }

            system.addBookSeriesWithTitleAndAuthorList(seriesTitle, booksInSeries, authors);
            System.out.println("Book series added.");
        } catch (EmptyAuthorListException | BookSeriesNotASeriesException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}