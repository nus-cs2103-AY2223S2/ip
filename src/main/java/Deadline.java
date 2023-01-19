public class Deadline extends Task{
    private String date;
    public Deadline(String text, String date) {
        super(text);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by:" + date + ")";
    }
}
