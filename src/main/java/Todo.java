public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toCsv() {
        return "T," + super.toCsv();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
