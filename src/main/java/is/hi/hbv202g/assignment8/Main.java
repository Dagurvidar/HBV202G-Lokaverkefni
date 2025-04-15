package is.hi.hbv202g.assignment8;

import java.util.Scanner;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LibrarySystem system = new LibrarySystem();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        System.out.println("Filling library with books...");
        CreateBooks.fillLibrary(system);

        while (running) {
            System.out.println("\n--- Library System Menu ---");
            System.out.println("1. Add book with single author");
            System.out.println("2. Add book with multiple authors");
            System.out.println("3. Add student user");
            System.out.println("4. Add faculty member user");
            System.out.println("5. Borrow book");
            System.out.println("6. Return book");
            System.out.println("7. Extend lending");
            System.out.println("8. List all books");
            System.out.println("9. Quit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter book title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter author name: ");
                        String authorName = scanner.nextLine();
                        system.addBookWithTitleAndNameOfSingleAuthor(title, authorName);
                        System.out.println("Book added.");
                        break;

                    case 2:
                        System.out.print("Enter book title: ");
                        String multiTitle = scanner.nextLine();
                        List<Author> authors = new ArrayList<>();
                        while (true) {
                            System.out.print("Enter author name (or 'done' to finish): ");
                            String name = scanner.nextLine();
                            if (name.equalsIgnoreCase("done")) break;
                            authors.add(new Author(name));
                        }
                        system.addBookWithTitleAndAuthorList(multiTitle, authors);
                        System.out.println("Book added.");
                        break;

                    case 3:
                        System.out.print("Enter student name: ");
                        String studentName = scanner.nextLine();
                        System.out.print("Has fee been paid? (true/false): ");
                        boolean feePaid = scanner.nextBoolean();
                        scanner.nextLine();
                        system.addStudentUser(studentName, feePaid);
                        System.out.println("Student added.");
                        break;

                    case 4:
                        System.out.print("Enter faculty member name: ");
                        String facultyName = scanner.nextLine();
                        System.out.print("Enter department: ");
                        String dept = scanner.nextLine();
                        system.addFacultyMemberUser(facultyName, dept);
                        System.out.println("Faculty member added.");
                        break;

                    case 5:
                        System.out.print("Enter user name: ");
                        String borrower = scanner.nextLine();
                        System.out.print("Enter book title: ");
                        String bookToBorrow = scanner.nextLine();
                        User user = system.findUserByName(borrower);
                        Book book = system.findBookByTitle(bookToBorrow);
                        system.borrowBook(user, book);
                        System.out.println("Book borrowed.");
                        break;

                    case 6:
                        System.out.print("Enter user name: ");
                        String returner = scanner.nextLine();
                        System.out.print("Enter book title: ");
                        String bookToReturn = scanner.nextLine();
                        User userReturn = system.findUserByName(returner);
                        Book bookReturn = system.findBookByTitle(bookToReturn);
                        system.returnBook(userReturn, bookReturn);
                        System.out.println("Book returned.");
                        break;

                    case 7:
                        System.out.print("Enter faculty member name: ");
                        String fmName = scanner.nextLine();
                        System.out.print("Enter book title: ");
                        String bookToExtend = scanner.nextLine();
                        System.out.print("Enter new due date (yyyy-mm-dd): ");
                        String dateStr = scanner.nextLine();
                        LocalDate newDate = LocalDate.parse(dateStr);
                        FacultyMember fm = (FacultyMember) system.findUserByName(fmName);
                        Book bookExt = system.findBookByTitle(bookToExtend);
                        system.extendLending(fm, bookExt, newDate);
                        System.out.println("Lending extended.");
                        break;

                    case 8:
                        system.listAllBooks();
                        break;

                    case 9:
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
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}