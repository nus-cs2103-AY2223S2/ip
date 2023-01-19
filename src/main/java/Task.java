public class Task {
    private int id;
    private String description;

    private boolean isDone;

    public Task(int id, String description) {
        this.id = id;
        this.description = description;
        this.isDone = false;
    }

    public String mark() {
        this.isDone = true;
        return String.format("\tNice! I've marked this task as done:\n\t  [X] %s", this.description);
    }

    public String unmark() {
        this.isDone = false;
        return String.format("\tOK, I've marked this task as not done yet:\n\t  [ ] %s", this.description);
    }


    @Override
    public String toString() {
        if (this.isDone) {
            return String.format("\t%d.[X] %s", this.id, this.description);
        }
        return String.format("\t%d.[ ] %s", this.id, this.description);
    }

}
