package ClassInterface;

/**
 * Methods to be implemented by all the business classes of the BeadItUpJa
 * project
 */

public interface Operations {

    /*
     * Adds the created object to the database
     */
    public abstract boolean create();

    /*
     * Edits the object in the database
     */
    public abstract boolean edit();

    /*
     * Provides a list of objects of this class type from the database
     */
    public abstract String[] view();

    /*
     * Returns the id for this object
     */
    public abstract int getID();

    /*
     * Returns the string identifier for this object
     */
    public abstract String toString();

}
