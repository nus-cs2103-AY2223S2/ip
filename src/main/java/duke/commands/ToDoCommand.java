package duke.commands;

import duke.Storage;
import duke.Ui;

import duke.exceptions.DukeExceptions;
import duke.exceptions.ForgottenArgumentException;

import duke.tasks.TaskList;
import duke.tasks.ToDo;

public class ToDoCommand implements Command {
    private String input;

    public ToDoCommand(String input) {
        this.input = input;
    }

    /**
     * Creates a new ToDo, adds it to the TaskList, and displays confirmation message of addition. If there are any
     * errors from creating a new ToDo, the error is displayed.
     *
     * @param taskList the TaskList the new ToDo is added to
     * @param ui the Ui needed to display according messages
     * @param storage the Storage used during this session
     * @throws ForgottenArgumentException If no description for the ToDo is given
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeExceptions {
        try {
            String description = input.substring(5);
            ToDo newToDo = new ToDo(description);
            taskList.addTask(newToDo);
            ui.showAddedMessage(newToDo, taskList);
        } catch (StringIndexOutOfBoundsException e) {
            throw new ForgottenArgumentException();
        }
    }
}