/**
 * ToDo is a type of Task.
 * This is the most primitive version of Task
 * @author EL
 */
public class ToDo extends Task {

    public ToDo(String task_name) {
        super(task_name);
    }
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
