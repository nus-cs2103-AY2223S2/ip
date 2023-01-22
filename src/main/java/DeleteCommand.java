/**
 * Represents Duke's delete function.
 */
public class DeleteCommand extends Command {
    public DeleteCommand() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws DukeException {
        Integer taskNum = ui.getTaskNum();
        String s = tasks.delete(taskNum);
        store.saveToFile(tasks);
        ui.printWithPartition("\tNoted. I've removed this task:\n\t  " + s + "\n" + "\tNow you have "
                + Integer.toString(tasks.size()) + " tasks in the list.\n");
    };
}
