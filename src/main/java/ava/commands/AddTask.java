package Ava.Commands;
import Ava.Ava.TASK_TYPE;
import Ava.Exceptions.AvaException;
import Ava.Exceptions.CommandNotFoundException;
import Ava.Storage;
import Ava.TaskList;
import Ava.Tasks.Deadline;
import Ava.Tasks.Event;
import Ava.Tasks.Task;
import Ava.Tasks.Todo;

public class AddTask implements AvaCommand {
    String[] parsedInput;
    private final static String MESSAGE = "Added this task for you: ";
    Task newTask;
    TASK_TYPE task;

    public AddTask(String[] input, TASK_TYPE t) throws CommandNotFoundException {
        this.parsedInput = input;
        this.task = t;
        this.isInputCorrect();
    }

    @Override
    public boolean run(TaskList t, Storage s) throws AvaException {
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

    @Override
    public String output(String formatSpace) {
        return MESSAGE + "\n" + formatSpace + newTask.getRepresentation();
    }

    /**
     * Before Adding the Task , check if the parsedInputArray is correct
     * @throws CommandNotFoundException
     */
    private void  isInputCorrect() throws CommandNotFoundException  {
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
