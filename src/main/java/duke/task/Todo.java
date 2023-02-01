package duke.task;

public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toSaveFormat() {
        return "T||" + super.toSaveFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public static Todo fromSaveFormat(String savedData) {
        String[] inputs = savedData.split("\\|\\|");
        Todo generatedTodo = new Todo(inputs[2]);
        if (inputs[1].equals("1")) {
            generatedTodo.setCompleted(true);
        }
        return generatedTodo;
    }
}
