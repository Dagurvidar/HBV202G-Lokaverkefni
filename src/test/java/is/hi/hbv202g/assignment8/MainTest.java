package is.hi.hbv202g.assignment8;

import org.junit.Test;

/**
 * Unit test for the {@link Main} class.
 *
 * This test simply ensures that the {@link LibrarySystem} can be instantiated without throwing exceptions.
 */
public class MainTest {

    /**
     * Tests that the {@link LibrarySystem} can be instantiated successfully.
     */
    @Test
    public void shouldBePossibleToInstantiateLibrarySystem() {
        new LibrarySystem();
    }
}
