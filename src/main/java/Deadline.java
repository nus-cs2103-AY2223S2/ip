public class Deadline extends Task {
    private String dateTime;
    public Deadline(String str, boolean checked) {
        super(str, checked);
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return dateTime;
    }
}
