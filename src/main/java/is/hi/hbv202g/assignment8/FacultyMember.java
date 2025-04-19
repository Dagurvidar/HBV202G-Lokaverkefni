package is.hi.hbv202g.assignment8;

/**
 * Represents a faculty member in the library system.
 * Extends from {@link User} class.
 */
public class FacultyMember extends User {
    private String department;

    /**
     * Creates a new FacultyMember with a name and department.
     *
     * @param name The name of the faculty member.
     * @param department The department of the faculty member.
     */
    public FacultyMember(String name, String department) {
        super(name);
        this.department = department;
    }

    /**
     * Gets the department of the faculty member.
     *
     * @return The department of the faculty member.
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Sets the department of the faculty member.
     *
     * @param department The new department for the faculty member.
     */
    public void setDepartment(String department) {
        this.department = department;
    }
}
