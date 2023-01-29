package Ava.Commands;

import Ava.Storage;
import Ava.TaskList;

public class ListTask implements AvaCommand {

    private TaskList tasks;
    private final static String MESSAGE = "You've got a busy day ahead:";

    @Override
    public boolean run(TaskList t, Storage s){
        this.tasks = t;
        return true;
    }

    @Override
    public String output(String formatSpace){
        return MESSAGE +"\n" + tasks.formatTasks(formatSpace);
    }

}
