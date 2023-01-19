public class Deadline extends Task {

    private String dateBy;

    public Deadline(String title, String dateBy) {
        super(title);
        this.dateBy = dateBy;
    }

    @Override
    public String toString() {
        String doneString = this.getDone() ? "X" : " ";
        return String.format("[D][%s] %s (by: %s)", doneString, this.getTitle(), this.dateBy);
    }

}
