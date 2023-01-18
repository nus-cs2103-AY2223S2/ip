public class Todo extends Task{
    public Todo(String taskName) {
        super(taskName);
    }

    public static void processTodo(String command, TaskList lst) throws DukeException{
        String taskName = command.trim();
        if (taskName.isEmpty()) {
            throw new DukeException("todo");
        } else {
            Todo todo = new Todo(taskName);
            lst.addTask(todo);
            Duke.printLine();
            System.out.println("Got it! I've added: ");
            System.out.println(" " + todo.toString());
            lst.printSize();
            Duke.printLine();
        }
    }

    @Override
    public String toString() {
        String s;
        if (this.completed) {
            s = "[T]" + super.toString();
        } else {
            s = "[T]" + super.toString();
        }
        return s;
    }
}
