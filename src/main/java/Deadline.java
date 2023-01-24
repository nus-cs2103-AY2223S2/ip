public class Deadline extends Task {
    protected String ddl;
    protected String name;

    public Deadline(String name) {
        super(name);
        this.name = name.substring("deadline".length() + 1, name.indexOf("/by") - 1);
        this.ddl = name.substring(name.indexOf("/by") + 4);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString().substring(0, "[ ] ".length()) +
                this.name + " (by: " + this.ddl + ")";
    }


}
