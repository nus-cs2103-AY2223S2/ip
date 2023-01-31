public class ListCommand extends Command {
    public void execute(Tasklist tasklist, Ui ui, Storage storage) {
        ui.printTasks(tasklist);
    }
}