package controllers;

import java.util.function.Supplier;

import entities.TaskList;
import enums.CommandType;

/**
 * Represents the List Command.
 * The list command can be used to list all tasks.
 */
public class ListCommand extends Command {

    /**
     * Initializes a list command.
     */
    public ListCommand() {
        super(CommandType.LIST);
    }

    @Override
    public void execute(Supplier<? extends TaskList> taskList) {
        TaskList store = taskList.get();
        System.out.println(store.listTasks());
    }
}
