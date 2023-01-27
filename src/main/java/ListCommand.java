public class ListCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.showTasks(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
