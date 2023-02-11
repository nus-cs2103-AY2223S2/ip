package command;

import duke.Ui;

import task.Task;
import task.TaskList;

public class UndoCommand {

    private static Task task;
    private static int number;
    private static String previousInstruction = " ";

    public UndoCommand(Task task) {
        UndoCommand.task = task;
    }

    public static void delete(Task task, int number) {
        UndoCommand.task = task;
        UndoCommand.number = number;
        UndoCommand.previousInstruction = "DELETE";
    }

    public static void mark(int number) {
        UndoCommand.number = number + 1;
        UndoCommand.previousInstruction = "MARK";
    }

    public static void unmark(int number) {
        UndoCommand.number = number + 1;
        UndoCommand.previousInstruction = "UNMARK";
    }

    public static void find() {
        UndoCommand.previousInstruction = "FIND";
    }

    public static void deadline(Task task) {
        UndoCommand.task = task;
        UndoCommand.previousInstruction = "DEADLINE";
    }

    public static void event(Task task) {
        UndoCommand.task = task;
        UndoCommand.previousInstruction = "EVENT";
    }

    public static void todo(Task task) {
        UndoCommand.task = task;
        UndoCommand.previousInstruction = "TODO";
    }

    public static void list() {
        UndoCommand.previousInstruction = "LIST";
    }


    public static String undo() throws Exception{
        switch (previousInstruction) {
            case "DELETE" :
                TaskList.addToList(task, number);
                task.plus();
                return Ui.printUndoSuccess();
            case "MARK" :
                new UnMarkCommand(String.valueOf(number)).unmark();
                return Ui.printUndoSuccess();
            case "UNMARK" :
                new MarkCommand(String.valueOf(number)).mark();
                return Ui.printUndoSuccess();
            case "DEADLINE" :
            case "EVENT" :
            case "TODO" :
                TaskList.remove(task);
                task.minus();
                return Ui.printUndoSuccess();
            default :
                return Ui.printUndoFail();
        }

    }

}
