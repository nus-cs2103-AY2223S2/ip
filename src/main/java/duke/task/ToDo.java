package duke.task;

/**
 * This class creates a Todo type object which inherits from Task
 *
 * @author He Shuimei
 * @version 0
 */
public class ToDo extends Task {
    public ToDo(String desc, boolean done) {
        super(desc, done);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
