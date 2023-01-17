public class TodoCommand implements Command {
    private Task newTask;
    public TodoCommand(Task newTask) {
        this.newTask = newTask;
    }

    public void execute(TaskList list) {
        list.add(newTask);;
    }

    public boolean isExit() {
        return false;
    }
}
