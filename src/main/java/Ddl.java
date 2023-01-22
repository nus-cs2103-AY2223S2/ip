public class Ddl extends Task {
    private String time;
    public Ddl(String input, String time) {
        super(input);
        this.time = time;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + time + ")";
    }

}
