public class Deadline extends Task{

    protected String dueDate;

    public static Deadline create(String str) throws DukeException{
        if (str.length() < 1) {
            throw new DukeException();
        } else {
            String[] text = str.substring(1).split(" /by ");
            if (text.length < 2) {
                throw new DukeException();
            } else {
                return new Deadline(text[0], text[1]);
            }
        }
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
