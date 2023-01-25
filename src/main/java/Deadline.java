import java.time.LocalDate;

// Deadline class returns result that is type [D] and a deadline
class Deadline extends Task {
    private final LocalDate date;
    private final String time;
    private final String[] period;

    public Deadline(String name, String frame) {
        super(name);
        this.period = frame.split(" ");
        if (period[0].contains("/")) {
            this.date = LocalDate.parse(period[0].replaceAll("/", "-"));
        } else {
            this.date = LocalDate.parse(period[0]);
        }
        this.time = period[1];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + date.getDayOfMonth() + " "
                + date.getMonth() + " " + date.getYear() + ", " + time + " )";
    }
}
