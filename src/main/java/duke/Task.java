package duke;

public abstract class Task {
    public enum TaskSymbol {
        TODO ("T"),
        EVENT ("E"),
        DEADLINE ("D");
        private final String symbol;
        TaskSymbol(String symbol) {
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
    protected boolean isDone;
    protected TaskSymbol symbol;

    /**
     * Constructor for task
     * @param description Description of the task
     */
    protected Task(String description, TaskSymbol symbol) {
        this.description = description;
        this.isDone = false;
        this.symbol = symbol;
    }

    /**
     * Gets the status icon for the current task. ("X" or " ")
     * @return String representing the status
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Getter function to return the description of the task
     * @return A String representing the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter function to mark the task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Setter function to mark the as undone
     */
    public void unmarkAsDone() {
        isDone = false;
    }

    /**
     * Getter function for isDone
     * @return isDone
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Getter function for the task type
     * @return Task type enum
     */
    public TaskSymbol getTaskType() {
        return this.symbol;
    }

    /**
     * toString function to represent the task as a string
     * @return String representation of the task
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.symbol.getSymbol(), getStatusIcon(), getDescription());
    }

}