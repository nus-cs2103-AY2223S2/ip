/**
 * Given a task description, construct a Task ToDo object.
 *
 * @author JamesLiuZX
 * AY2223-S2 CS2103T
 */

public class TaskToDo extends Task {
    public TaskToDo(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toStringDb() {
        return String.format(
                "T|%s",
                super.toStringDb()
        );
    }

}
