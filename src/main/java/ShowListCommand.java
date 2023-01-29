public class ShowListCommand extends Command{



    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listUI(tasks);
    }

    public boolean isExit() {
        return false;
    }

}
