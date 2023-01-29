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

    @Override
    public String toString() {
        String str = this.getStr();
        boolean checked = this.isChecked();
        String dateTime = this.getDateTime();
        if (checked) {
            return "[D][X] " + str + " (by: " + dateTime + ")";
        } else {
            return "[D][ ] " + str + " (by: " + dateTime + ")";
        }
    }
}
