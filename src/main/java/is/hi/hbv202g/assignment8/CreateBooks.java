package is.hi.hbv202g.assignment8;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for creating and adding books and book series
 * to a LibrarySystem instance.
 */
public class CreateBooks {

    /**
     * Populates the given LibrarySystem with a selection of standalone books
     * and book series.
     *
     * @param librarySystem the LibrarySystem to populate
     */
    public static void fillLibrary(LibrarySystem librarySystem) {
        try {
            // === Standalone books ===
            librarySystem.addBookWithTitleAndNameOfSingleAuthor("1984", "George Orwell");
            librarySystem.addBookWithTitleAndNameOfSingleAuthor("To Kill a Mockingbird", "Harper Lee");
            librarySystem.addBookWithTitleAndNameOfSingleAuthor("The Great Gatsby", "F. Scott Fitzgerald");

            // === Book Series: The Lord of the Rings ===
            Author tolkien = new Author("J.R.R. Tolkien");
            List<Book> lotrBooks = new ArrayList<>();
            lotrBooks.add(new Book("The Fellowship of the Ring", tolkien.getName()));
            lotrBooks.add(new Book("The Two Towers", tolkien.getName()));
            lotrBooks.add(new Book("The Return of the King", tolkien.getName()));
            librarySystem.addBookSeriesWithTitleAndNameOfSingleAuthor("Lord of the Rings", lotrBooks, tolkien.getName());

            // === Book Series: His Dark Materials ===
            Author pullman = new Author("Philip Pullman");
            List<Book> hdmBooks = new ArrayList<>();
            hdmBooks.add(new Book("Northern Lights", pullman.getName()));
            hdmBooks.add(new Book("The Subtle Knife", pullman.getName()));
            hdmBooks.add(new Book("The Amber Spyglass", pullman.getName()));
            librarySystem.addBookSeriesWithTitleAndNameOfSingleAuthor("His Dark Materials", hdmBooks, pullman.getName());

            System.out.println("Library filled successfully!");

        } catch (EmptyAuthorListException | BookSeriesNotASeriesException e) {
            System.err.println("Failed to add book(s): " + e.getMessage());

        }
    }
}
