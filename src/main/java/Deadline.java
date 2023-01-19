public class Deadline extends Task {
    private String deadline;

    private Deadline(String description, String deadline) {
        super("D", description);
        this.deadline = deadline;
    }

    public static Deadline to(String str) {
        String target = " /by ";
        String description, deadline;
        int index = str.indexOf(target);
        description = str.substring(0, index);
        deadline = str.substring(index + 5);
        return new Deadline(description, deadline);
    }

    @Override
    public String toString() {
        String str = super.toString();
        str += " " + "(by: " + this.deadline + ")" + "\n";
        return str;
    }
}
