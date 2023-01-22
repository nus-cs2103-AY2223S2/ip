package controllers;

import entities.TaskList;
import enums.CommandType;
import exceptions.DukeException;

import java.util.function.Supplier;
import java.util.regex.Matcher;

public class DeleteCommand extends Command {
    private final String args;

    public DeleteCommand(String args) {
        super(CommandType.DELETE);
        this.args = args;
    }

    /**
     * {@inheritDoc}
     * The method verifies the command and delete the task specified.
     */
    @Override
    public void execute(Supplier<? extends TaskList> taskList) throws DukeException {
        TaskList store = taskList.get();
        Matcher m = VALID_NUMBER.matcher(args);
        if (m.find()) {
            store.deleteTask(Integer.parseInt(m.group()));
        } else {
            throw new DukeException("Invalid format. Please ensure that you specify the task number.");
        }
    }
}
