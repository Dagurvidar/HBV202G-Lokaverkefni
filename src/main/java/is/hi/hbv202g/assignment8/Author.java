package is.hi.hbv202g.assignment8;

/**
 * Represents an author of a book in the library system.
 */
public class Author {
    private String name;

    /**
     * Constructs a new Author with the given name.
     *
     * @param name the name of the author
     */
    public Author(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the author.
     *
     * @return the author's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets a new name for the author.
     *
     * @param name the new name of the author
     */
    public void setName(String name) {
        this.name = name;
    }
}
