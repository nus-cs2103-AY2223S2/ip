
package Duke.Tasks;

/**
 * Contains the ToDo class
 */
public class ToDo extends Task {
    /**
     * The contructor for ToDo Task
     *
     * @param description
     *
     */
    public ToDo(String description){
        super(description);
    }

    /**
     * Obtain the string representation of the todo task.
     *
     * @return string representation of the todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();

    }
    /**
     * The changeFormat method for change the format when  saving/Writing  tasks in data.txt file
     * @return String
     */
    @Override
    public String changeFormat() {

        return String.format("T %s", super.changeFormat());
    }
}
