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