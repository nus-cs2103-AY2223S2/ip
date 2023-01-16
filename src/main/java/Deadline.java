public class Deadline extends Task {
    protected String type;
    protected String time;

    public Deadline(String name, String time) {
        super(name);
        this.time = time;
        this.type = "D";
    }

    public Deadline(String description) {
        super();
        int indexOfBy = description.indexOf("/by");
        this.time = description.substring(indexOfBy + "/by ".length());
        this.name = description.substring(0, indexOfBy - " ".length());
        this.type = "D";
    }

    public static String parseTime(String s) {
        return s.substring(s.indexOf("/by") + "/by ".length());
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", this.type, super.toString(), this.time);
    }
}
