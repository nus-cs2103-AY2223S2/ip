package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.time.LocalDate;

public class AddDeadlineCommand extends Command {
    private String deadlineDescription;
    private LocalDate by;
    public AddDeadlineCommand(String deadlineDescription, LocalDate by) {
        super();
        this.deadlineDescription = deadlineDescription;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task deadline = tasks.addDeadline(deadlineDescription, by);
        ui.formResponse("New deadline task added: " + deadline);
        storage.save(tasks.getList());
    }
}
