package controllers;

import entities.TaskList;
import enums.CommandType;

import java.util.function.Supplier;

public class ListCommand extends Command {

    public ListCommand() {
        super(CommandType.LIST);
    }

    /**
     * {@inheritDoc}
     * The method verifies the command and list all tasks in the store.
     */
    @Override
    public void execute(Supplier<? extends TaskList> taskList) {
        TaskList store = taskList.get();
        System.out.println(store.listTasks());
    }
}
