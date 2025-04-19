package is.hi.hbv202g.assignment8;

/**
 * An abstract base class representing a user of the library system.
 * Each user has a name.
 */
public abstract class User {
    private String name;

    /**
     * Constructs a User with the specified name.
     *
     * @param name the name of the user
     */
    public User(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the user.
     *
     * @return the user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name the new name for the user
     */
    public void setName(String name) {
        this.name = name;
    }
}
