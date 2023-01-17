public class ToDo extends Task {
    public ToDo(String title) {
        super(title);
        this.type = "[T]";
    }

    public ToDo(String title, boolean done) {
        super(title);
        this.type = "[T]";
        this.done = done;
    }

    @Override
    public String toData() {
        return this.type + " | " + (this.done ? "1" : "0") + " | " + this.title;
    }

}
