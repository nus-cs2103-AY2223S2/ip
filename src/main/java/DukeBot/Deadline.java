package DukeBot;

public class Deadline extends Task {

    private static final String typeToString = "[D]";
    private final String deadline;

    public Deadline(String task, String deadline) {
        super(task);
        this.type = Types.DEADLINE;
        this.deadline = deadline;
    }

    @Override
    public String status() {
        String status = this.completed ? "[X]" : "[ ]";
        return typeToString + status + details + " (by:" + this.deadline + ")";
    }
}
