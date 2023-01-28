public class ListCommand extends Command {

    void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBunny();
        ui.list(tasks);
    }

}