package duke.tasks;

public class ToDo extends Task {
    public ToDo(int id, String description) {
        super(id, description);
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
        return this.id + ". [T][" + statusIcon + "] " + this.desc;
    }
}
