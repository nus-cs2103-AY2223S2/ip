public class Deadline extends Task {
    protected String day;

    public Deadline(String description,String day){
        super(description);
        this.day = day;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.day + ")";
    }
}
