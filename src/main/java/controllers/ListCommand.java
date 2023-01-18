package controllers;

import entities.TaskList;
import enums.CommandType;

public class ListCommand extends Command {

    public ListCommand() {
        super(CommandType.LIST);
    }

    @Override
    public void execute() {
        System.out.println(TaskList.listTasks());
    }
}
