package is.hi.hbv202g.assignment8;

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

            Book fellowship = new Book("The Fellowship of the Ring", List.of(tolkien));
            Book twoTowers = new Book("The Two Towers", List.of(tolkien));
            Book returnKing = new Book("The Return of the King", List.of(tolkien));

            List<Book> lotrBooks = List.of(fellowship, twoTowers, returnKing);
            BookSeries lotrSeries = new BookSeries("The Lord of the Rings", lotrBooks, List.of(tolkien));

            // Register each book from the series individually
            for (Book book : lotrBooks) {
                librarySystem.addBookWithTitleAndAuthorList(book.getTitle(), book.getAuthors());
            }

            // === Book Series: His Dark Materials ===
            Author pullman = new Author("Philip Pullman");

            Book northernLights = new Book("Northern Lights", List.of(pullman));
            Book subtleKnife = new Book("The Subtle Knife", List.of(pullman));
            Book amberSpyglass = new Book("The Amber Spyglass", List.of(pullman));

            List<Book> hdmBooks = List.of(northernLights, subtleKnife, amberSpyglass);
            BookSeries hisDarkMaterials = new BookSeries("His Dark Materials", hdmBooks, List.of(pullman));

            // Register each book from this series too
            for (Book book : hdmBooks) {
                librarySystem.addBookWithTitleAndAuthorList(book.getTitle(), book.getAuthors());
            }

            System.out.println("Library filled successfully!");

        } catch (EmptyAuthorListException e) {
            System.err.println("Failed to add book(s) due to missing author(s): " + e.getMessage());
        }
    }
}
