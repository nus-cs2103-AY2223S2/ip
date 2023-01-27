package src.main.java.command;

public class ListCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, TaskStore taskStore) {
        Ui.output(tasks.list());
    }
}
