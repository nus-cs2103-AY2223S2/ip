package roody.commands;

import java.time.LocalDate;
import java.util.ArrayList;

import roody.Storage;
import roody.Ui;
import roody.tasks.Event;
import roody.tasks.Task;

/**
 * Represents a command to make an event task.
 */
public class MakeEventCommand extends Command {
    private String description;
    private LocalDate start;
    private LocalDate end;

    /**
     * Creates an event task.
     * @param description Description.
     * @param start Starting date.
     * @param end End date.
     */
    public MakeEventCommand(String description, LocalDate start, LocalDate end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }
    @Override
    public String execute(ArrayList<Task> taskList, Ui ui, Storage storage) {
        Task task = new Event(description, start, end);
        taskList.add(task);
        return ui.showAddTask(task, taskList.size());
    }
}
