package tasks;
import tasks.Task;

/**
 * Represents Todo type task
 */
public class Todo extends Task {
    /**
     * Constructor for Todo object
     * @param desc Description of task
     */
    public Todo(String desc) {
        super(desc);
    }

    @Override
    public void updateFields(String[] fields, String[] values) {
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].equals("desc")) {
                this.description = values[i];
            }
        }
    };

    @Override
    public String toFileString() {
        String mark = this.isDone()? "1": "0";
        return String.format("T | %s | %s", mark, this.getDescription());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
