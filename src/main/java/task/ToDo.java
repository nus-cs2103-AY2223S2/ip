package task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public String serialise() {
        return String.format("Todo,%s,%s", super.getStatusIcon(), super.getDescription());
    }

    public static Task deserialise(String data) {
        String arr[] = data.split(",");

        boolean isDone = arr[1].equals("X");
        String description = arr[2];

        return new ToDo(description);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
