package is.hi.hbv202g.assignment8;

import java.util.ArrayList;
import java.util.List;

public class BookSeries implements ReadingMaterial {
    private boolean isAvailable = false;
    private final List<ReadingMaterial> books;

    public BookSeries(List<ReadingMaterial> books) {
        this.books = books;
    }

    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public List<Author> getAuthors() {
        return List.of();
    }

    @Override
    public boolean isAvailable() {
        return isAvailable;
    }
}
