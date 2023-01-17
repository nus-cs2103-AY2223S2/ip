public class DeadlineCommand implements Command{
    private Task newTask;
    public DeadlineCommand(Task newTask) {
        this.newTask = newTask;
    }

    public void execute(TaskList list) {
        list.add(newTask);;
    }

    public boolean isExit() {
        return false;
    }
}
