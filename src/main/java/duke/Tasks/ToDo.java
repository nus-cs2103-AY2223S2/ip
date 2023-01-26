package duke.Tasks;

public class ToDo extends Task {
    public ToDo(int id, String description) {
        super(id, description);
    }
    public String toFile() {
        return "TD|" + this.isDone + "|" + this.desc;
    }
    @Override
    public String toString() {
        String statusIcon = this.getStatusIcon();
        return this.id + ". [T][" + statusIcon + "] " + this.desc;
    }
}
