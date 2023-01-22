import java.time.format.DateTimeParseException;

/**
 * Represents Duke's deadline function
 */
public class AddDeadlineCommand extends Command {
    public AddDeadlineCommand() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws DukeException {
        String[] data = ui.getDeadline();
        try {
            Deadline temp = new Deadline(data[0], data[1]);
            tasks.add(temp);
            store.saveToFile(tasks);
            ui.printWithPartition("\tGot it. I've added this task:\n" + "\t  " + temp.toString()
                    + "\n\tNow you have " + Integer.toString(tasks.size()) + " tasks in the list.\n");
        } catch (DateTimeParseException e) {
            throw new DukeException("The event command should be used like this:\n"
                    + "\tevent {name} /from {YYYY-MM-DD} /to {YYYY-MM-DD}");
        }

    };

}
