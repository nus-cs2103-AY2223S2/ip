public class Deadline extends Task{

    protected String dueDate;

    public static Deadline create(String str) {
        String[] text = str.split(" /by ");
        return new Deadline(text[0], text[1]);
    }

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dueDate + ")";
    }
}
