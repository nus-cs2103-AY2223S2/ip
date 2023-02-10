package command;

import duke.Storage;
import duke.Ui;

import exceptions.DukeException;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;

/**
 * A class that handles the creation of an Event command
 */
public class EventCommand extends Command {

    private String input;

    /**
     * constructor for eventCommand
     *
     * @param input
     */
    public EventCommand(String input) {
        super();
        this.input = input;
    }

    /***
     * Adds an Event task into the taskList and updates storage files
     * if duplicate detected, no changes made to taskList or storage
     * @param taskList
     * @param ui
     * @param storage
     * @throws DukeException
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        input = input.substring(6);
        String[] s = input.split("/from");
        String[] timing = s[1].split("/to");
        String from = timing[0].trim();
        String to = timing[1].trim();
        String description = s[0].trim();
        Task task = new Event(description, from, to);
        if (taskList.checkDuplicate(task)) {
            return ui.printDuplicates(task);
        } else {
            taskList.add(task);
            storage.writeFile(taskList);
            return ui.printAddTask(taskList.size(), task);
        }
    }
}
