package roody.commands;

import java.time.LocalDate;
import java.util.ArrayList;

import roody.Storage;
import roody.Ui;
import roody.tasks.Deadline;
import roody.tasks.Task;

/**
 * Represents a command to make a deadline task.
 */
public class MakeDeadlineCommand extends Command {
    private String description;
    private LocalDate deadline;

    /**
     * Creates a deadline task.
     * @param description Description of deadline.
     * @param deadline Date of deadline.
     */
    public MakeDeadlineCommand(String description, LocalDate deadline) {
        this.description = description;
        this.deadline = deadline;
    }
    @Override
    public String execute(ArrayList<Task> taskList, Ui ui, Storage storage) {
        Task task = new Deadline(description, deadline);
        taskList.add(task);
        return ui.showAddTask(task, taskList.size());
    }
}
