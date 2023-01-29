package Ava.Commands;

import Ava.Exceptions.AvaException;
import Ava.Exceptions.NonExistentTask;
import Ava.Storage;
import Ava.TaskList;
import Ava.Tasks.Task;

import static java.lang.Character.isDigit;

/**
 * Unmarks Task
 */
public class UnmarkTask implements AvaCommand {
    String[] parsedInput;
    Task changedTask;
    private final static String MESSAGE = "Ahh! More work to be done on this task:";

    /**
     * UnmarkTask Contructor
     * @param parsedInput string array of the index of the task to be marked.
     */
    public UnmarkTask(String[] parsedInput){
        this.parsedInput = parsedInput;
    }

    /**
     * Retreive unmarked task
     * @param t a TaskList object
     * @param s a Storage object
     * @return true indicate program running
     * @throws AvaException idicate array index out of bounds
     */
    @Override
    public boolean run(TaskList t, Storage s) throws AvaException {
        this.changedTask = t.unmark(Integer.valueOf(this.parsedInput[0]));
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
