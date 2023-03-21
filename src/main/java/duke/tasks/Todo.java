package duke.tasks;

/**
 * Encapsulates a tasks a user has to do.
 *
 * @author Tan Matthew Simon Castaneda
 * @version CS2103 AY22/23 Semester 2
 */

public class Todo extends Task {

    private static final String type = "T";


    /**
     * Constructs a todo object specified by user input.
     *
     * @param name Name of the task to be done.
     */
    public Todo(String name) {
        super(name);
    }

    //getter for type

    public static String getType() {
        return type;
    }

    /**
     *  Converts the task to be done to its string representation.
     *
     * @return A string representation of the task to be done.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
