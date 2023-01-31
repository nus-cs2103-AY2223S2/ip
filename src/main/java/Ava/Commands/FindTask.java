package Ava.commands;


import Ava.exceptions.AvaException;
import Ava.exceptions.CommandNotFoundException;
import Ava.TaskList;
import Ava.Storage;

/**
 * finds Task
 */
public class FindTask implements AvaCommand {
    private TaskList tasks;
    private String keyword;
    private final static String MESSAGE = "Here's what I found: ";
    String[] parsedInput;

    /**
     * @param parsedInput contains keyword to find
     */
    public FindTask(String[] parsedInput) throws AvaException {
        this.parsedInput = parsedInput;
    }

    /**
     * Find the corresponding keyword in the taskList
     * @param t a TaskList Object
     * @param s a Storage Object
     * @return
     */
    @Override
    public boolean run(TaskList t, Storage s) throws AvaException {
        this.keyword = this.parsedInput[0];
        this.tasks = t;
        return true;
    }

    /**
     *
     * @param formatSpace indentation required by UI
     */
    @Override
    public String output(String formatSpace){
        return MESSAGE + "\n" + this.tasks.find(keyword, formatSpace);
    }

    /**
     * Before Finding the Task , check if the parsedInputArray is correct
     * @throws CommandNotFoundException
     */
    private void  isInputCorrect() throws CommandNotFoundException  {
        if (this.parsedInput.length != 1){
            throw new CommandNotFoundException("");
        }
    }
}
