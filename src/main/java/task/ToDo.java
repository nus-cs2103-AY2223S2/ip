package task;

import util.DukeException;

public class ToDo extends Task {
    public ToDo(String description, boolean status) {
        super(description, status);
    }

    public String serialise() {
        return String.format("Todo,%s,%s", super.getStatus(), super.getDescription());
    }

    public static Task deserialise(String data) {
        String arr[] = data.split(",");

        boolean isDone = Boolean.parseBoolean(arr[1]);
        String description = arr[2];

        return new ToDo(description, isDone);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
