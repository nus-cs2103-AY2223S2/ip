package duke.task;

/**
 * The Todo task.
 * Inherits from the superclass Task.
 */
public class Todo extends Task {

    public Todo(String taskDescription) {
        super(taskDescription);
        super.typeOfTask = 'T';
        
    }

    @Override
    public String toString() {
        return "[" + super.typeOfTask + "]" + super.toString();
    }
}
