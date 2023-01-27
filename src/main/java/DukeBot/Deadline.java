package DukeBot;

import java.util.ArrayList;
import java.util.Objects;

public class Deadline extends Task {

    private static final String typeToString = "D";
    private final String deadline;

    public Deadline(String task, String deadline) {
        super(task);
        this.type = Types.DEADLINE;
        this.deadline = deadline;
    }

    public Deadline(String[] data) {
        super(data[2]);
        this.completed = Objects.equals(data[1], "X");
        this.deadline = data[2];
    }

    @Override
    public String status() {
        String status = this.completed ? "[X]" : "[ ]";
        return "[" + typeToString + "]" + status + this.details + " (by:" + this.deadline + ")";
    }

    @Override
    public ArrayList<String> data() {
        ArrayList<String> data = new ArrayList<>();
        data.add(typeToString);
        data.add(this.completed ? "X" : " ");
        data.add(this.details);
        data.add(this.deadline);
        return data;
    }
}
