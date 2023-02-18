package tigerlily.commands;

import tigerlily.Storage;
import tigerlily.Ui;

import tigerlily.exceptions.DukeExceptions;
import tigerlily.exceptions.ForgottenArgumentException;

import tigerlily.tasks.TaskList;
import tigerlily.tasks.ToDo;

public class ToDoCommand implements Command {
    private String input;

    /**
     * Constructor for ToDoCommand
     * @param input the input given to create a ToDo
     */
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
     * @return the confirmation message that the new ToDo has been added successfully
     * @throws ForgottenArgumentException If no description for the ToDo is given
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeExceptions {
        try {
            String description = input.substring(5);
            ToDo newToDo = new ToDo(description);
            taskList.addTask(newToDo);
            return ui.showAddedMessage(newToDo, taskList);
        } catch (StringIndexOutOfBoundsException e) {
            throw new ForgottenArgumentException();
        }
    }
}