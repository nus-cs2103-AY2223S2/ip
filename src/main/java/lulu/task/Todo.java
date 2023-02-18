package lulu.task;

/**
 * Represents a ToDo task.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * @return a String representation of a ToDo
     */
    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }

    /**
     * This method is used to convert a ToDo's task description and details to a String
     * to be written to a save file.
     *
     * @return a String to be written to a save file.
     */
    @Override
    public String toMemory() {
        int i = this.isDone ? 1 : 0;
        return ("T`" + i + "`" + this.description + '\n');
    }

    /**
     * This method is used to update the description of a ToDo.
     *
     * @param text the new description of the ToDo
     */
    @Override
    public void update(String text) {
        String[] updateInformation = text.split(" ");
        String update = updateInformation[0].toUpperCase();
        switch (update) {
        case "DESCRIPTION":
            this.description = updateInformation[1];
            break;
        }
    }
}
