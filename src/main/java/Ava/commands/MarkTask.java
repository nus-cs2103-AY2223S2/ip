package Ava.commands;


import Ava.Storage;
import Ava.TaskList;
import Ava.exceptions.AvaException;
import Ava.exceptions.CommandNotFoundException;
import Ava.exceptions.NonExistentTask;
import Ava.tasks.Task;

import static java.lang.Character.isDigit;

/**
 * Marks Task
 */
public class MarkTask implements AvaCommand {
    String[] parsedInput;
    Task changedTask;
    private final static String MESSAGE = "Yayy! One more task done:";

    /**
     * MarkTask Contructor
     * @param parsedInput string array of the index of the task to be marked.
     */
    public MarkTask(String[] parsedInput) throws NonExistentTask {

        this.parsedInput = parsedInput;
        this.isCorrectInput();
    }

    /**
     * Retreive marked task
     * @param t a TaskList object
     * @param s a Storage object
     * @return true indicate program running
     * @throws AvaException idicate array index out of bounds
     */
    @Override
    public boolean run(TaskList t, Storage s) throws AvaException {
        assert this.parsedInput.length == 1 && isDigit(parsedInput[0].charAt(0)): "Invalid Input";


        this.changedTask = t.mark(Integer.valueOf(this.parsedInput[0]));
        t.updateStorage(s);
        return true;
    }


    /**
     * @param formatSpace identation required by the UI
     * @return  return UI Message + Task representation
     */
    @Override
    public String output(String formatSpace){
        return MESSAGE + "\n" + formatSpace + changedTask.getRepresentation();
    }

    /**
     * Before marking the Task , check if the parsedInputArray is correct
     * @throws NonExistentTask parsedInput is incorrect
     */
    private void isCorrectInput() throws NonExistentTask {
        if (parsedInput.length != 1  || !isDigit(parsedInput[0].charAt(0))){
            throw new NonExistentTask("");
        }
    }
}
