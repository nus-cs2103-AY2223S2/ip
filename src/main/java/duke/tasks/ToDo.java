package duke.tasks;

/**
 * Class that models a ToDo task
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    /**
     * Process ToDo to String to store in duke.txt
     * @return Processed String
     *
     */
    @Override
    public String toFile() {
        return "TD|" + this.isDone + "|" + this.desc;
    }

    @Override
    public String toString() {
        String statusIcon = this.getStatusIcon();
        return "[T][" + statusIcon + "] " + this.desc;
    }
}
