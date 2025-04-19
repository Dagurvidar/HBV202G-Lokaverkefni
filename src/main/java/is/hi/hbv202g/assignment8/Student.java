package is.hi.hbv202g.assignment8;

/**
 * Represents a student user in the library system.
 * A student has a name and a status indicating whether their lending fee has been paid.
 */
public class Student extends User {
    private boolean feePaid;

    /**
     * Constructs a new Student with the specified name and fee payment status.
     *
     * @param name     the name of the student
     * @param feePaid  true if the lending fee has been paid, false otherwise
     */
    public Student(String name, boolean feePaid) {
        super(name);
        this.feePaid = feePaid;
    }

    /**
     * Returns whether the student has paid the lending fee.
     *
     * @return true if the fee has been paid, false otherwise
     */
    public boolean isFeePaid() {
        return feePaid;
    }

    /**
     * Sets the fee payment status for the student.
     *
     * @param feePaid true if the fee has been paid, false otherwise
     */
    public void setFeePaid(boolean feePaid) {
        this.feePaid = feePaid;
    }
}
