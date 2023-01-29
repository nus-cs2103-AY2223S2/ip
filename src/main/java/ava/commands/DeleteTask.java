package Ava.Commands;

import Ava.Exceptions.AvaException;
import Ava.Exceptions.NonExistentTask;
import Ava.Storage;
import Ava.TaskList;
import Ava.Tasks.Task;

import static java.lang.Character.isDigit;

public class DeleteTask implements AvaCommand {
    String[] parsedInput;
    Task deletedTask;
    private final static String MESSAGE = "Oof! I have deleted this task:";

    public DeleteTask(String[] parsedInput) throws AvaException{
        this.parsedInput = parsedInput;
        this.isCorrectInput();
    }

    @Override
    public boolean run(TaskList t, Storage s) throws AvaException {
        int index = Integer.valueOf(parsedInput[0]);
        this.deletedTask = t.deleteTask(index);
        t.updateStorage(s);
        return true;
    }

    @Override
    public String output(String formatSpace){
        return MESSAGE + "\n" + formatSpace + this.deletedTask.getRepresentation();
    }

    private void isCorrectInput() throws NonExistentTask {
        if (parsedInput.length != 1  || !isDigit(parsedInput[0].charAt(0))){
            throw new NonExistentTask("");
        }
    }
}
