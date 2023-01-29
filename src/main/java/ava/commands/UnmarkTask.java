package Ava.Commands;

import Ava.Exceptions.AvaException;
import Ava.Exceptions.NonExistentTask;
import Ava.Storage;
import Ava.TaskList;
import Ava.Tasks.Task;

import static java.lang.Character.isDigit;

public class UnmarkTask implements AvaCommand {
    String[] parsedInput;
    Task changedTask;
    private final static String MESSAGE = "Ahh! More work to be done on this task:";

    public UnmarkTask(String[] parsedInput){
        this.parsedInput = parsedInput;
    }

    @Override
    public boolean run(TaskList t, Storage s) throws AvaException {
        this.changedTask = t.unmark(Integer.valueOf(this.parsedInput[0]));
        t.updateStorage(s);
        return true;
    }


    @Override
    public String output(String formatSpace){
        return MESSAGE + "\n" + formatSpace + changedTask.getRepresentation();
    }

    private void isCorrectInput() throws NonExistentTask {
        if (parsedInput.length != 1  || !isDigit(parsedInput[0].charAt(0))){
            throw new NonExistentTask("");
        }
    }
}
