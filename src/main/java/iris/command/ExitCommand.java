package iris.command;

import iris.TaskList;
import iris.Ui;
import iris.TaskStore;

public class ExitCommand extends Command{
    @Override
    public boolean isEnd() {
        return true;
    }
    private static final String EXIT_TEXT = "Bye! Hope to see you soon!";

    @Override
    public void execute(TaskList tasks, Ui ui, TaskStore taskStore) {
        Ui.output(EXIT_TEXT);
    }
}
