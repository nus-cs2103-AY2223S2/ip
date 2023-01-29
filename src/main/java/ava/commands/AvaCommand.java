package ava.commands;
import ava.exceptions.AvaException;
import ava.Storage;
import ava.TaskList;

public interface AvaCommand {
    /**
     * Implement the logic of the command
     * @param t
     * @param s
     * @throws AvaException
     */
    public boolean run(TaskList t, Storage s) throws AvaException;

    /**
     * Return the reuired String Output.
     * @return
     */
    public String output(String formatSpace);

}
