package duke;

public class Task {
    private String done;
    private String name;

    /**
     * Constructor for tasks
     * @param name
     */
    public Task(String name) {
        this.done = " ";
        this.name = name;
    }

    /**
     * Marks task as done
     */
    public void setDone() {
        this.done = "X";
    }

    /**
     * Marks task as undone
     */
    public void setUndone() {
        this.done = " ";
    }

    /**
     * Checks if task is done
     * @return Done status
     */
    public boolean isDone() {
        if (this.done.equals(" ")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Gets description of task
     * @return Description of task
     */
    public String getName() {
        return this.name;
    }

    /**
     * Formats the task to the format for saving
     * @return Formatted task string
     */
    public String toSave() {
        if (this.done.equals("X")) {
            return "1 | " + this.name + "\n";
        } else {
            return "0 | " + this.name + "\n";
        }
    }
    @Override
    public String toString() {
        return "[" + done + "] " + name;
    }
}
