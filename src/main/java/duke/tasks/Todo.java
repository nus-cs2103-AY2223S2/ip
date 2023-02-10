package duke.tasks;

public class Todo extends Task {
    private String desc;

    public Todo(int id, String description) {
        super(id);
        desc = description;
    }

    public String description() {
        return desc;
    }

    @Override
    public String toString() {
        String isDone = isCompleted() ? "X" : " ";
        return String.format("[T][%s] %s", isDone, description());
    }

    @Override
    public String serialize() {
        String isDone = isCompleted() ? "1" : "0";
        return String.format("T|%s|%s|%s", id(), isDone, description());
    }

    public static Todo deserialize(String s) {
        String[] parts = s.split("\\|");
        Todo todo = new Todo(Integer.parseInt(parts[1]), parts[3]);
        if (parts[2].equals("1")) {
            todo.markCompleted();
        }
        return todo;
    }
}
