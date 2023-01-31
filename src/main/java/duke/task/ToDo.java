package duke.task;

import java.util.ArrayList;
import java.util.Objects;

public class ToDo extends Task {

    private static final String typeToString = "T";

    public ToDo(String task) {
        super(task);
        this.type = Types.TODO;
    }

    public ToDo(String[] data) {
        super(data[2]);
        this.completed = Objects.equals(data[1], "X");
        this.details = data[2];
    }

    @Override
    public String status() {
        String status = this.completed ? "[X] " : "[ ] ";
        return "[" + typeToString + "]" + status + " " + this.details;
    }

    @Override
    public ArrayList<String> data() {
        ArrayList<String> data = new ArrayList<>();
        data.add(typeToString);
        data.add(this.completed ? "X" : " ");
        data.add(this.details);
        return data;
    }
}
