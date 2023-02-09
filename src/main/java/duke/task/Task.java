package duke.task;

import duke.Duke;
import duke.DukeException;

public abstract class Task {
    public enum TaskIcon {
        TODO ("T"),
        EVENT ("E"),
        DEADLINE ("D");
        private final String symbol;
        TaskIcon(String symbol) {
            this.symbol = symbol;
        }

        /**
         * Getter function for the symbol of the enum
         * @return Symbol string
         */
        public String getSymbol() {
            return symbol;
        }
    };
    protected String description;
    protected String tags;
    protected boolean isDone;
    protected TaskIcon taskIcon;

    /**
     * Returns a Task object.
     * @param description Description of the task.
     */
    protected Task(String description, TaskIcon taskIcon) throws DukeException {
        this.description = description;
        this.isDone = false;
        this.taskIcon = taskIcon;
        if (this.description.equals("")) {
            throw new DukeException("The description of a Task cannot be empty.");
        }
    }

    protected Task (String description, TaskIcon taskIcon, String tags) throws DukeException {
        this(description, taskIcon);
        this.tags = tags;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkAsDone() {
        isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public TaskIcon getTaskIcon() {
        return this.taskIcon;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.taskIcon.getSymbol(), getStatusIcon(), getDescription());
    }

}