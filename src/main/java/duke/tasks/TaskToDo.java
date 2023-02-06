package duke.tasks;

/**
 * Encapsulates a simple task to be done.
 *
 * @author JamesLiuZX
 *     AY2223-S2 CS2103T
 */
public class TaskToDo extends Task {
    /**
     * Constructor for creating a TaskToDo object.
     *
     * @param s        The task's description.
     */
    public TaskToDo(String s) {
        super(s);
    }

    /**
     * Returns string representation of a TaskToDo object which users can see in the command line.
     *
     * @return String representation of TaskToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns string representation of a TaskToDo object that is stored in the duke.functions.Duke.txt file.
     *
     * @return String representation of TaskTodo.
     */
    @Override
    public String toStringDb() {
        return String.format(
                "T|%s",
                super.toStringDb()
        );
    }

}
