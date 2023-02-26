package Ava.commands;


import Ava.Storage;
import Ava.TaskList;
import Ava.exceptions.CommandNotFound;


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
     * @throws CommandNotFound inidicate incorrectly parsed input
     */
    public FindTask(String[] parsedInput) throws CommandNotFound {
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
        //Already Check parsedInput is valid , if still execute until here then input is invalid
        assert parsedInput.length == 1 : "Invalid Input";

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
     * @throws CommandNotFound
     */
    private void  isInputCorrect() throws CommandNotFound {
        if (this.parsedInput.length != 1){
            throw new CommandNotFound("");
        }
    }
}
