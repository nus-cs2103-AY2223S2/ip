package task;

/**

 Todo is a subclass of {@link Task} that adds its own implementation for the toSaveFormat() and toString() methods.
 @author Your Name (if applicable)
 */
public class Todo extends Task{

    /**
     Constructor to create a new Todo instance with the specified name.
     @param name the name of the Todo task.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     Overridden method that returns the Todo task in the format "T[mark][divider][task name]" where
     mark is "1" if the task is marked done, and "0" otherwise. divider is the static final {@link Task#divider}.
     @return the Todo task in the format "T[mark][divider][task name]"
     */
    @Override
    public String toSaveFormat() {
        return "T" + super.toSaveFormat();
    }

    /**
     Overridden method that returns the Todo task as a string in the format "[T][status icon][task name]" where
     status icon is "X" if the task is marked done, and " " otherwise.
     @return the Todo task as a string in the format "[T][status icon][task name]"
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
