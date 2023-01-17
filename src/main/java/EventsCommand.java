public class EventsCommand implements Command{
    private Task newTask;
    public EventsCommand(Task newTask) {
        this.newTask = newTask;
    }

    public void execute(TaskList list) {
        list.add(newTask);;
    }

    public boolean isExit() {
        return false;
    }
}