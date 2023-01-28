package src.main.c4po;

public class ListCommand extends Command{

    public ListCommand() {

    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printList();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
