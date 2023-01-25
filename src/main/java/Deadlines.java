public class Deadlines extends Task {
    private String time;

    public Deadlines(String name, String time) {
        super(name);
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }

    public String toString() {
        if (this.getStatus()) {
            return "[D][X] " + this.getName() + " (by: " + this.getTime() + ")";
        } else {
            return "[D][ ] " + this.getName() + " (by: " + this.getTime() + ")";
        }
    }
}
