package Duke.task;

/**
 * Represents task of the type todo.
 */
public class Todo extends Task {
    public Todo(String string) {
        super(string);
    }

    @Override
    public String toString() {
        return "todo";
    }

    /**
   * Method return a String with information about a todo task:
   * type and marked or not.
   */
    @Override
    public String taskString() {
        String mark;
        if (this.isMark()) {
            mark = "[X]";
        } else {
            mark = "[ ]";
        }
        String type = "[T]";
        return type + mark + " "
            + this.getString().trim();
    }
}
