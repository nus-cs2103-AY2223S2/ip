package Ava.commands;


import Ava.Storage;
import Ava.TaskList;

/**
 * Exits the Program
 */
public class Exit implements AvaCommand {

    /**
     *
     * @param t a TaskList object
     * @param s a Storage object
     * @return False to indicate program has stopped runnign
     */
    private final static String MESSAGE = "Have a nice day !!";;

    @Override
    public boolean run(TaskList t, Storage s){

        return false;
    }

    @Override
    public String output(String formatSpace){

        return MESSAGE;
    }
}
