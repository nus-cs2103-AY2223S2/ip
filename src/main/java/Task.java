import java.io.Serializable;

public abstract class Task implements Serializable {
    private static final String COMPLETED = " [O] ";
    private static final String NOT_COMPLETED = " [ ] ";
    static final String EXTRAS_FORMAT_TEMPLATE = " (%s)";
    private static final String FORMAT_EXCEPTION = "Invalid format for creating Task";
    private static final String MARKED_RESPONSE = "Well done. Task has been marked as completed:\n";
    private static final String UNMARKED_RESPONSE = "Got it. Task has been unmarked:\n";
    private final String name;
    private boolean isCompleted;
    Task(String name) throws DukeException {
        if (name.isEmpty()) {
            throw new DukeException(FORMAT_EXCEPTION);
        }
        this.name = name;
        isCompleted = false;
    }

    public void markTask() {
        isCompleted = true;
        System.out.println(DukeIO.wrapContent(MARKED_RESPONSE + DukeIO.INDENT + this));
    }

    public void unmarkTask() {
        isCompleted = false;
        System.out.println(DukeIO.wrapContent(UNMARKED_RESPONSE + DukeIO.INDENT + this));
    }

    static Task parseTaskFromInput(String input) throws DukeException {
        if (input.startsWith(ToDoTask.INPUT_PREFIX)) {
            return ToDoTask.createToDo(input);
        } else if (input.startsWith(DeadlineTask.INPUT_PREFIX)) {
            return  DeadlineTask.createDeadline(input);
        } else if (input.startsWith(EventTask.INPUT_PREFIX)) {
            return EventTask.createEvent(input);
        }
        return null;
    }

    static boolean isCreateTaskCommand(String input) {
        return input.startsWith(ToDoTask.INPUT_PREFIX) || input.startsWith(DeadlineTask.INPUT_PREFIX)
                || input.startsWith(EventTask.INPUT_PREFIX);

    }

    @Override
    public String toString() {
        return (isCompleted ? COMPLETED : NOT_COMPLETED) + name;
    }
}
