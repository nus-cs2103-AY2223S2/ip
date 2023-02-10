package duke.tasks;

public class Deadline extends Task {
    private String desc;
    private String dueDate;

    public Deadline(int id, String description, String dueDate) {
        super(id);
        desc = description;
        this.dueDate = dueDate;
    }

    public String description() {
        return desc;
    }

    @Override
    public String toString() {
        String isDone = isCompleted() ? "X" : " ";
        return String.format("[D][%s] %s (by: %s)", isDone, description(), dueDate);
    }

    @Override
    public String serialize() {
        String isDone = isCompleted() ? "1" : "0";
        return String.format("D|%s|%s|%s|%s", id(), isDone, description(), dueDate);
    }

    public static Deadline deserialize(String s) {
        String[] parts = s.split("\\|");
        Deadline deadline = new Deadline(Integer.parseInt(parts[1]), parts[3], parts[4]);
        if (parts[2].equals("1")) {
            deadline.markCompleted();
        }
        return deadline;
    }
}
