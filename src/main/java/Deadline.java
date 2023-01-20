//Deadline is one of 3 types of Task, so it must be a child of Task

public class Deadline extends Task {

    protected String dateline;

    public Deadline(String name, String dateline) {
        super(name);
        this.dateline = dateline;
    }

    @Override
    public String toString() {
        //making sure printing matches the expected format
        return "[D]" + super.toString() + " " + "(by: " + dateline + ")";
    }
}
