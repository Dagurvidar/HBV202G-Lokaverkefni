package is.hi.hbv202g.assignment8;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
    private User student;
    private User facultyMember;

    @Before
    public void setUp() {
        student = new Student("Alice", true);
        facultyMember = new FacultyMember("Bob", "Computer Science");
    }

    @Test
    public void testUserConstructor() {
        assertEquals("Alice", student.getName());
        assertEquals("Bob", facultyMember.getName());
    }

    @Test
    public void testSetName() {
        student.setName("Charlie");
        assertEquals("Charlie", student.getName());

        facultyMember.setName("David");
        assertEquals("David", facultyMember.getName());
    }
}
