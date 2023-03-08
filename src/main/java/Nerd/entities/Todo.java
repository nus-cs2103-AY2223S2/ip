package Nerd.entities;

/**
 * Represents the Todo Task of the Chat bot.
 */
public class Todo extends Task {

    /**
     * Instantiates an Todo Object that can be placed into the TaskList.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Process the current Todo object to be saved into a text file.
     *
     * @return A String representing the Todo task to be saved.
     */
    @Override
    public String toSave() {
        if (super.isDone == true) {
            return String.format("T | 1 | %s\n", super.getDescription());
        }
        return String.format("T | 0 | %s\n", super.getDescription());
    }

    /**
     * Process the current Todo object to be displayed.
     *
     * @return A String representing the Todo task to be displayed by the User Interface.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
