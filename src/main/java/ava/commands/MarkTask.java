package Ava.Commands;

import Ava.Exceptions.AvaException;
import Ava.Exceptions.NonExistentTask;
import Ava.Storage;
import Ava.TaskList;
import Ava.Tasks.Task;

import static java.lang.Character.isDigit;

public class MarkTask implements AvaCommand {
    String[] parsedInput;
    Task changedTask;
    private final static String MESSAGE = "Yayy! One more task done:";

    public MarkTask(String[] parsedInput) {
        this.parsedInput = parsedInput;
    }

    @Override
    public boolean run(TaskList t, Storage s) throws AvaException {
        this.changedTask = t.mark(Integer.valueOf(this.parsedInput[0]));
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
