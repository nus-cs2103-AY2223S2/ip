public class Deadline extends Task {
    protected String deadline; 

    public Deadline(String name, String date) {
        super(name);
        this.deadline = date;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }

}
