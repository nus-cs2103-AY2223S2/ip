package Ava.commands;


import Ava.Storage;
import Ava.TaskList;

/**
 * Lists task
 */
public class ListTask implements AvaCommand {

    private TaskList tasks;
    private final static String MESSAGE = "You've got a busy day ahead:";

    /**
     * Retreives fromatted tasks from TaskList
     * @param t a TaskList object
     * @param s s Storage object
     * @return true indicate program still running
     */
    @Override
    public boolean run(TaskList t, Storage s){
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
