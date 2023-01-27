public class Todo extends Task{

    public Todo (String description) {

        super(description);
    }
    public Todo (boolean isDone, String description) {
        super(description);
        this.isDone = isDone;
    }

    @Override
    public String save() {
        return "T|" + (this.isDone ? 1 : 0) + "|" + this.description + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
