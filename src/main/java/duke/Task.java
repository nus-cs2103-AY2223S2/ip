package duke;

public class Task {
    private String name;
    private boolean isCompleted;

    /**
     * Constructor for a Task instance.
     *
     * @param name the name of this Task as a simple description.
     */
    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    /**
     * Marks the task as completed.
     * It does not matter whether the task has previously been marked as completed.
     */
    public void makeCompleted() {
        this.isCompleted = true;
    }

    /**
     * Returns the string representation of the task.
     * A [ ] indicates an uncompleted task, while a [X] indicates a completed task.
     *
     * @return the desired string representation of the task with its description.
     */
    @Override
    public String toString() {
        String completedString = "";
        if (this.isCompleted) {
            completedString += "[X] ";
        } else {
            completedString += "[ ] ";
        }

        completedString += this.name;
        return completedString;
    }

    /**
     * Returns the string representation of the task.
     * A [ ] indicates an uncompleted task, while a [X] indicates a completed task.
     *
     * @return the desired string representation of the task with its description.
     */
    public String parse() {
        String completedString = "";
        if (this.isCompleted) {
            completedString += "[X] ";
        } else {
            completedString += "[ ] ";
        }

        completedString += this.name;
        return completedString;
    }

    public boolean contains(String keyword) {
        return name.contains(keyword);
    }
}
