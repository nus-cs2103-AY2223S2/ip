public class ListCommand extends Command {

    @Override
    public void execute(TaskList list, Storage store, Ui ui) {
        ui.showMessage("Here are the tasks in your list:");
        if (list.size() == 0) {
            ui.showMessage("OOPS!!! Your list is empty.");
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.print(i + 1 + ".");
            System.out.println(list.get(i).toString());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
