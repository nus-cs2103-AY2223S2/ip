package controllers;

import entities.TaskList;
import enums.CommandType;

import java.util.function.Supplier;

public class ListCommand extends Command {

    public ListCommand() {
        super(CommandType.LIST);
    }

    @Override
    public void execute(Supplier<? extends TaskList> taskList) {
        TaskList store = taskList.get();
        System.out.println(store.listTasks());
    }
}
