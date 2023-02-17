package Ava.commands;


import Ava.Storage;
import Ava.TaskList;
import Ava.exceptions.CannotWriteToFile;
import Ava.exceptions.NonExistentTask;
import Ava.exceptions.CommandNotFoundException;
import Ava.exceptions.CannotCreateDirectory;
import Ava.exceptions.CannotReadFromFile;
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
     * @throws CommandNotFoundException incorrect parsedInput
     */
    public DeleteTask(String[] parsedInput) throws CommandNotFoundException {
        this.parsedInput = parsedInput;
        this.isCorrectInput();
    }

    /**
     * Deletes Task from TaskList and Storage.
     * @param t TaskList Object
     * @param s Storage Object
     * @return Boolean value indicating programs still running
     * @throws NonExistentTask idicate array index out of bounds
     * @throws CannotCreateDirectory indicating directory could not be created
     * @throws CannotWriteToFile indicating that storage was unable to write to File
     * @throws CannotReadFromFile indicating that storage was inable to read from File
     */
    @Override
    public boolean run(TaskList t, Storage s) throws NonExistentTask, CannotReadFromFile,
            CannotCreateDirectory, CannotWriteToFile {
        //Already Check parsedInput is valid , if still execute until here then input is invalid
        assert parsedInput.length == 1: "Invalid Input";

        int index = Integer.valueOf(parsedInput[0]);
        this.deletedTask = t.deleteTask(index); // Throws NonExistentTask
        t.updateStorage(s); // Throws CannotReadFromFile CannotCreateDirectory, CannotWriteToFile
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
     * @throws CommandNotFoundException parsedInput is incorrect
     */
    private void isCorrectInput() throws CommandNotFoundException {
        if (parsedInput.length != 1  || !isDigit(parsedInput[0].charAt(0))) {
            throw new CommandNotFoundException("");
        }
    }
}
