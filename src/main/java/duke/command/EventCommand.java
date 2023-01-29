package command;

import java.util.Arrays;

import dukeexception.CommandException;
import parser.DateTimeParser;
import storage.Storage;
import task.Event;
import task.Task;
import taskList.TaskList;
import ui.Ui;

public class EventCommand extends Command {
    public EventCommand(String request) {
        super(request);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String[] values = this.unwrap();
            Task task = new Event(values[0], values[1], values[2]);

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

        int fromIndex = Arrays.asList(values).indexOf("/from");
        int toIndex = Arrays.asList(values).indexOf("/to");
        String description = String.join(" ", Arrays.copyOfRange(values, 1, fromIndex));
        String from = String.join(" ", Arrays.copyOfRange(values, fromIndex + 1, toIndex));
        from = DateTimeParser.parse(from);
        String to = String.join(" ", Arrays.copyOfRange(values, toIndex + 1, values.length));
        to = DateTimeParser.parse(to);

        return new String[] { description, from, to };
    }

    @Override
    public void checkRequestRequirement() throws CommandException {
        String[] values = super.getRequest().split(" ");
        String message = "";

        if (values.length <= 1) {
            message = "Description cannot be empty";
        }

        if (!super.getRequest().contains("/from")) {
            message = "Your request must include /from";
        }

        if (!super.getRequest().contains("/to")) {
            message = "Your request must include /to";
        }

        if (super.getRequest().indexOf("/from") > super.getRequest().indexOf("/to")) {
            message = "Your request must be in the following order: <Description> /from <Datetime> /to <Datetime>";
        }

        if (!message.isEmpty()) {
            throw new CommandException(message);
        }
    }
}
