package Ava.commands;

import Ava.Ava.TASK_TYPE;
import Ava.Storage;
import Ava.TaskList;
import Ava.exceptions.CannotWriteToFile;
import Ava.exceptions.DateTimeNotParsed;
import Ava.exceptions.CommandNotFoundException;
import Ava.exceptions.CannotCreateDirectory;
import Ava.tasks.Deadline;
import Ava.tasks.Event;
import Ava.tasks.Task;
import Ava.tasks.Todo;

/**
 * Command to Add Task to the TaskList
 */
public class AddTask implements AvaCommand {
    String[] parsedInput;
    private final static String MESSAGE = "Added this task for you: ";
    Task newTask;
    TASK_TYPE task;

    /**
     * Constructor for AddTask Command
     * @param input string array containing parsed task message
     * @param t enum containing the task types
     * @throws CommandNotFoundException parsedInput is incorrect
     */
    public AddTask(String[] input, TASK_TYPE t) throws CommandNotFoundException {
        this.parsedInput = input;
        this.task = t;
        this.isInputCorrect();
    }

    /**
     * Add Task to TaskList and Add Task to Storage
     * @param t TaskList object
     * @param s Storage object
     * @return Nothing
     * @throws CannotCreateDirectory indicating directory could not be created
     * @throws CannotWriteToFile indicates that storage was unable to write to File
     * @throws DateTimeNotParsed indicates Date/Time Format passed is incorrect
     */
    @Override
    public boolean run(TaskList t, Storage s) throws CannotCreateDirectory, CannotWriteToFile, DateTimeNotParsed {

       switch (this.task) {
       case DEADLINE:
           newTask = new Deadline(parsedInput[0], parsedInput[1]);
           break;
       case EVENT:
           newTask = new Event(parsedInput[0] , parsedInput[1] ,parsedInput[2]);
           break;
       default:
           newTask = new Todo(parsedInput[0]);
       }
       // Write the Current Task To Storage
        s.writeToStorage(newTask.getStorageFormat());

       // Add the Task tasklist
        t.addTask(newTask);
        return true;
    }

    /**
     *
     * @param formatSpace identation required by the UI
     * @return  return UI Message + Task representation
     */
    @Override
    public String output(String formatSpace) {

        return MESSAGE + "\n" + formatSpace + newTask.getRepresentation();
    }

    /**
     * Before Adding the Task , check if the parsedInputArray is correct
     * @throws CommandNotFoundException parsedInput is incorrect
     */
    private void  isInputCorrect() throws CommandNotFoundException {
        switch (this.task) {
        case EVENT:
             if (this.parsedInput.length != 3){
                 throw new CommandNotFoundException("");
             }
             break;
        case DEADLINE:
            if (this.parsedInput.length != 2){
                throw new CommandNotFoundException("");
            }
            break;
        default:
            if (this.parsedInput.length != 1){
                throw new CommandNotFoundException("");
            }
        }
    }
}
