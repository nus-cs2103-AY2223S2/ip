public class Deadlines extends Task {
    private String time;

    public Deadlines(String name, String time) {
        super(name);
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }
}
