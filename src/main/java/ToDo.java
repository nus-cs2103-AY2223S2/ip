public class ToDo extends Task {
    protected String taskType;

    public ToDo(String taskName) {
        super(taskName);
    }

    public static void createToDo(String command, TaskTracker t) throws DukeInputError{
        String[] input = command.split(" ");
        if (input.length <= 1) throw new DukeInputError("todo");
        StringBuilder taskName = new StringBuilder();
        for (int i = 1; i < input.length; i++) {
            taskName.append(input[i]);
            if (i < input.length - 1) {
                taskName.append(" ");
            }
        }
        t.addTask(new ToDo(taskName.toString()));
    }

    @Override
    public String toString() {
        return String.format("  %s%s %s", displayType(), displayMark(), this.taskName);
    }

}
