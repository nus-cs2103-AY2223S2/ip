package controllers;

import entities.TaskList;
import enums.CommandType;
import exceptions.DukeException;

import java.util.regex.Matcher;

public class DeleteCommand extends Command {
    private final String args;


    public DeleteCommand(String args) {
        super(CommandType.DELETE);
        this.args = args;
    }

    @Override
    public void execute() throws DukeException {
        Matcher m = NUMBERS.matcher(args);
        if (m.find()) {
            Integer key = Integer.parseInt(m.group());
            TaskList.deleteTask(key);
        } else {
            throw new DukeException("Invalid format. Please ensure that you specify the task number.");
        }
    }
}
