package task;

public class Deadline extends Task {
    private String by;
    private String date;
    private String byFormatted;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.byFormatted = super.dateFormatter(this.by);
    }

    public String serialise() {
        return String.format("Deadline,%s,%s,%s,%s", super.getStatusIcon(),
                super.getDescription(), this.by);
    }
    public static Task deserialise(String data) {
        String arr[] = data.split(",");

        boolean isDone = arr[1].equals("X");
        String description = arr[2];
        String by = arr[3];

        return new Deadline(description, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.byFormatted + ")";
    }

    // add Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException
    //when user does not input the task;
}
