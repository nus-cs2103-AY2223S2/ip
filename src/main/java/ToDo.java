public class ToDo extends Task {

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
        ToDo todo = new ToDo(taskName.toString());
        t.addTask(todo);
        ToDo.saveTaskData(todo, 1);
    }

    @Override
    public String toString() {
        return String.format("%s", super.toString());
    }

}
