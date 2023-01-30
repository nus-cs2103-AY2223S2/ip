package duke.tasks;

public class ToDoTask extends Task {
    public ToDoTask(String title) {
        super(title);
    }

    public String save() {
        String status = this.isDone ? "DONE/+/" : "NOTDONE/+/";
        return "TODO/+/" + status + this.title + "/+/" + "\n";
    }

    @Override
    public String toString() {
        String status = this.isDone ? "[x] " : "[ ] ";
        return "[T]" + status + this.title;
    }
}
