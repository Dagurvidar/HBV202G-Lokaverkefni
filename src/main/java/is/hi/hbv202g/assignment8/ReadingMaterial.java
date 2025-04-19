package is.hi.hbv202g.assignment8;

import java.util.List;

public interface ReadingMaterial {
    String getTitle();

    List<Author> getAuthors();

    boolean isAvailable();
}
