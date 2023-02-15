package Ava.commands;


import Ava.Storage;
import Ava.TaskList;
import Ava.exceptions.AvaException;
import Ava.exceptions.NonExistentTask;
import Ava.tasks.Task;

import static java.lang.Character.isDigit;

/**
 * Deletes task
 */
public class DeleteTask implements AvaCommand {
    String[] parsedInput;
    Task deletedTask;
    private final static String MESSAGE = "Oof! I have deleted this task:";

    /**
     * DeleteTask contructor
     * @param parsedInput string array containing parsed index of the task that needs to be deleted
     * @throws AvaException incorrect parsedInput
     */
    public DeleteTask(String[] parsedInput) throws AvaException {
        this.parsedInput = parsedInput;
        this.isCorrectInput();
    }

    /**
     * Deletes Task from TaskList and Storage.
     * @param t TaskList Object
     * @param s Storage Object
     * @return Boolean value indicating programs still running
     * @throws AvaException parsed Index is not correct or out of bounds.
     */
    @Override
    public boolean run(TaskList t, Storage s) throws AvaException {
        //Already Check parsedInput is valid , if still execute until here then input is invalid
        assert parsedInput.length == 1: "Invalid Input";

        int index = Integer.valueOf(parsedInput[0]);
        this.deletedTask = t.deleteTask(index);
        t.updateStorage(s);
        return true;
    }

    /**
     *
     * @param formatSpace identation required by the UI
     * @return  return UI Message + Deleted Task representation
     */
    @Override
    public String output(String formatSpace) {
        return MESSAGE + "\n" + formatSpace + this.deletedTask.getRepresentation();
    }

    /**
     * Before Deleting the Task , check if the parsedInputArray is correct
     * @throws NonExistentTask parsedInput is incorrect
     */
    private void isCorrectInput() throws NonExistentTask {
        if (parsedInput.length != 1  || !isDigit(parsedInput[0].charAt(0))) {
            throw new NonExistentTask("");
        }
    }
}
