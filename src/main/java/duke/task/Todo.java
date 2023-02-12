package duke.task;
public class Todo extends Task {

    protected String tag;
    public Todo(String description) {
        super(description);
        this.tag = "[T]";
    }
    public Todo(String description, boolean isMark) {
        super(description);
        this.tag = "[T]";
        super.markTask(isMark);
    }
    public String getTag() {
        return this.tag;
    }
    public String getDate() {
        return "";
    }
    @Override
    public String toString() {
        return this.tag + super.toString();
    }
}
