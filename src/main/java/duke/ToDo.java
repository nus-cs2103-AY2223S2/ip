package duke;

/**
 * A task with no deadline.
 */
class ToDo extends Task {

    /**
     * Constructs a new ToDo task.
     * @param keyword The keyword command to create a new ToDo task.
     * @param message The description of the task.
     * @param completed The completion status of the task.
     */
    public ToDo(String keyword, String message, Boolean completed) {
        super(keyword, message, completed);
    }

    @Override
    public String provideDetails() {
        return this.completed ? "[T]" + "[x] " + this.description
                : "[T]" + "[ ] " + this.description;
    }
}
