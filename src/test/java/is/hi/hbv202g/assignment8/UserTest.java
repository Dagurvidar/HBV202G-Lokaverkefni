package is.hi.hbv202g.assignment8;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for the {@link User} class and its subclasses {@link Student} and {@link FacultyMember}.
 */
public class UserTest {
    private User student;
    private User facultyMember;

    /**
     * Initializes test data before each test.
     * Creates instances of {@link Student} and {@link FacultyMember}.
     */
    @Before
    public void setUp() {
        student = new Student("Alice", true);
        facultyMember = new FacultyMember("Bob", "Computer Science");
    }

    /**
     * Tests that the {@link User} constructor correctly sets the user's name.
     */
    @Test
    public void testUserConstructor() {
        assertEquals("Alice", student.getName());
        assertEquals("Bob", facultyMember.getName());
    }

    /**
     * Tests that the {@link User#setName(String)} method correctly updates the user's name.
     */
    @Test
    public void testSetName() {
        student.setName("Charlie");
        assertEquals("Charlie", student.getName());

        facultyMember.setName("David");
        assertEquals("David", facultyMember.getName());
    }
}
