package Ava.commands;


import Ava.Storage;
import Ava.TaskList;
import Ava.exceptions.CommandNotFoundException;


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
     * @throws CommandNotFoundException inidicate incorrectly parsed input
     */
    public FindTask(String[] parsedInput) throws CommandNotFoundException {
        this.parsedInput = parsedInput;
        this.isInputCorrect();
    }

    /**
     * Find the corresponding keyword in the taskList
     * @param t a TaskList Object
     * @param s a Storage Object
     * @return
     */
    @Override
    public boolean run(TaskList t, Storage s) {
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
    private void  isInputCorrect() throws CommandNotFoundException {
        if (this.parsedInput.length != 1){
            throw new CommandNotFoundException("");
        }
    }
}
