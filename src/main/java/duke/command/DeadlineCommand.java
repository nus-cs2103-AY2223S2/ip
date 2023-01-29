package command;

import java.util.Arrays;
import dukeexception.CommandException;
import parser.DateTimeParser;
import storage.Storage;
import tasklist.TaskList;
import tasks.Deadline;
import tasks.Task;
import ui.Ui;

public class DeadlineCommand extends Command {

    public DeadlineCommand(String request) {
        super(request);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String[] values = this.unwrap();
            Task task = new Deadline(values[0], values[1]);

            tasks.add(task);
            ui.showTaskAdded(task, tasks.size());

            storage.save(tasks);
        } catch (CommandException error) {
            ui.showError(error);
        }
    }

    @Override
    public String[] unwrap() throws CommandException {
        String[] values = super.getRequest().split(" ");

        // Throws RequestExecution if there are any issues with the request
        checkRequestRequirement();

        int byIndex = Arrays.asList(values).indexOf("/by");
        String description = String.join(" ", Arrays.copyOfRange(values, 1, byIndex));
        String by = String.join(" ", Arrays.copyOfRange(values, byIndex + 1, values.length));
        by = DateTimeParser.parse(by);

        return new String[] { description, by };
    }

    @Override
    public void checkRequestRequirement() throws CommandException {
        String[] values = super.getRequest().split(" ");
        String message = "";

        if (values.length <= 1) {
            message = "Description cannot be empty";
        }

        if (!super.getRequest().contains("/by")) {
            message = "Your request must include /by";
        }

        if (!message.isEmpty()) {
            throw new CommandException(message);
        }
    }
}
