public class Todos extends Task {

    public Todos(String description) {
        this(false, description);
    }

    public Todos(boolean isDone, String description) {
        super(isDone, description);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String formatText() {
        String divider = " | ";
        String isMarked = this.isDone ? "1" : "0";
        return "T" + divider + isMarked + divider + this.description;
    }
}
