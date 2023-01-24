public class ListCommand extends Command {

    @Override
    public void execute(TaskList list) {
        String msg = "Here are the tasks in your list:\n" + list.listAllTasks();
        UI.echo(msg);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ListCommand;
    }
}
