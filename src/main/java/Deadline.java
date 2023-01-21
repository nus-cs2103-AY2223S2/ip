public class Deadline extends Task{
    protected String type = "[ D ]";
    protected String date;
    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return type + super.toString() + "by: " + this.date + " ";
    }
}
