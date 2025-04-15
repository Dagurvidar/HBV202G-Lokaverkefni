package is.hi.hbv202g.assignment8;

import java.util.List;

public interface ReadingMaterial {
    public String getTitle();
    public List<Author> getAuthors();
    public boolean isAvailable();
}
