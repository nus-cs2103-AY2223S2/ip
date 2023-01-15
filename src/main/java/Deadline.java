public class Deadline extends Task {

    String dueDate;

    Deadline(String content, String dueDate) {
        super(content);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.dueDate);
    }
}
