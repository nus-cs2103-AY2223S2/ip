public class ListCommand extends Command {


    /**
     * Displays all tasks in TaskList
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showToUser("Here are your tasks: ");
        int count = 1;
        for (Task t : tasks.getList()) {
            ui.showToUser(count + ". " + t.toString());
            count++;
        }
    }
}
