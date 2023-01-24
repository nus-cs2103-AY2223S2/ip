package tasktypes;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
        Task.numTask++;
    }

    @Override
    public String getSaveFormat() {
        String done;
        if (this.done) {
            done = "1";
        } else {
            done = "0";
        }
        return "T" + ",," + done + ",," + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

