/**
 * Represents Duke's todo command
 */
public class AddToDoCommand extends Command {
    public AddToDoCommand() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws DukeException {
        ToDo temp = new ToDo(ui.getName());
        tasks.add(temp);
        store.saveToFile(tasks);
        ui.printWithPartition("\tGot it. I've added this task:\n" + "\t  " + temp.toString()
                + "\n\tNow you have " + Integer.toString(tasks.size()) + " tasks in the list.\n");

    };

}
