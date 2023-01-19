public class Deadline extends Task {
    private String deadline;

    private Deadline(String description, String deadline) {
        super(TaskType.DEADLINE, description);
        this.deadline = deadline;
    }

    public static Deadline to(String str) {
        String target = " /by ";
        if (str.contains(target)) {
            String description, deadline;
            int index = str.indexOf(target);
            description = str.substring(0, index);
            deadline = str.substring(index + 5);
            if (!(description.equals("") && deadline.equals(""))) {
                return new Deadline(description, deadline);
            }
            throw new RuntimeException("Unable to create Deadline! Description or deadline was not filled in!\n");
        }
        throw new RuntimeException("Unable to create Deadline! Check your format!\n");
    }

    @Override
    public String toString() {
        String str = super.toString();
        str += " " + "(by: " + this.deadline + ")" + "\n";
        return str;
    }
}
