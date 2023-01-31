package alfred.task;
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String addToFile() {
        String str = String.format("T | %d | %s",
                isDone ? 1 : 0, this.description);
        return str + "\n";
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s",
                this.isDone ? "X" : " ", this.description);
    }
}
