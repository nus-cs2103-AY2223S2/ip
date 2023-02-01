package duke.task;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a ToDo task in Duke.
 */
public class ToDo extends Task {

    private static final String TYPE_TO_STRING = "T";

    /**
     * Represents a ToDo task in Duke.
     * @param task the task details.
     */
    public ToDo(String task) {
        super(task);
        this.type = Types.TODO;
    }

    /**
     * Represents a ToDo task in Duke.
     *
     * @param data an array of Strings with relevant information typically obtained from the database in Duke.
     */
    public ToDo(String[] data) {
        super(data[2]);
        this.isCompleted = Objects.equals(data[1], "X");
        this.details = data[2];
    }

    /**
     * Returns the status of the ToDo task.
     */
    @Override
    public String status() {
        String status = this.isCompleted ? "[X] " : "[ ] ";
        return "[" + TYPE_TO_STRING + "]" + status + " " + this.details;
    }

    /**
     * @return Returns all relevant information of the task in an ArrayList of Strings to be saved into the Database.
     */
    @Override
    public ArrayList<String> data() {
        ArrayList<String> data = new ArrayList<>();
        data.add(TYPE_TO_STRING);
        data.add(this.isCompleted ? "X" : " ");
        data.add(this.details);
        return data;
    }
}
