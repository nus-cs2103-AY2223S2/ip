package Ava.commands;

import Ava.Storage;
import Ava.TaskList;

public class SortTasks implements AvaCommand {
    private TaskList tasks;
    private final static String MESSAGE = "I have organised it for you:";

    /**
     * Sorts Task inside the list
     * @param t a TaskList object
     * @param s s Storage object
     * @return true indicate program still running
     */
    @Override
    public boolean run(TaskList t, Storage s){
        t.sortTasks();
        this.tasks = t;
        return true;
    }

    /**
     *
     * @param formatSpace identation required by the UI
     * @return  return UI Message + formatted tasks
     */
    @Override
    public String output(String formatSpace){

        return MESSAGE +"\n" + tasks.formatTasks(formatSpace);
    }
}
