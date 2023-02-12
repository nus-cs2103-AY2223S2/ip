package duke.Command;

import duke.TaskList;
import duke.UI;
import duke.Storage;

import duke.Exception.InvalidArgumentsException;

import duke.Task.Deadline;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {

    private final String name;
    private LocalDateTime deadline;

    public DeadlineCommand(String name, String deadline) throws InvalidArgumentsException {
        this.name = name;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            this.deadline = LocalDateTime.parse(deadline.trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentsException();
        }
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        Deadline dl = new Deadline(name, deadline);
        ui.showConfirmation(tasks.addTask(dl));
        storage.saveToFile(tasks.getTasks());
    }
}
