package is.hi.hbv202g.assignment8;

import java.util.List;

/**
 * Represents reading material such as a book or book series.
 * Provides methods for retrieving basic metadata and availability status.
 */
public interface ReadingMaterial {

    /**
     * Returns the title of the reading material.
     *
     * @return the title as a String
     */
    String getTitle();

    /**
     * Returns a list of authors of the reading material.
     *
     * @return a list of Author objects
     */
    List<Author> getAuthors();

    /**
     * Checks whether the reading material is currently available.
     *
     * @return true if available, false otherwise
     */
    boolean isAvailable();
}
