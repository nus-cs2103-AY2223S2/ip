package kira.ui;

import kira.exception.KiraException;
import kira.task.Deadline;
import kira.task.Event;
import kira.task.Task;
import kira.task.TaskType;
import kira.task.ToDo;

public class Parser {

    public Command command;
    private int index;
    private String taskArguments;
    
    public Parser(String input) throws KiraException {
        String[] temp = input.split(" ", 2);
        try {
            this.command = Command.valueOf(temp[0].toUpperCase());
            switch (this.command) {
            case BYE:
            case LIST:
            case TODAY:
                if (temp.length != 1) {
                    throw new KiraException("Incorrect use of command for BYE/LIST/TODAY");
                }
                break;
            case MARK:
            case UNMARK:
            case DELETE:
                if (temp.length != 2) {
                    throw new KiraException("Incorrect use of command for MARK/UNMARK/DELETE");
                }
                this.index = Integer.valueOf(temp[1]);
                break;
            case TODO:
            case DEADLINE:
            case EVENT:
                if (temp.length != 2) {
                    throw new KiraException("Incorrect use of command for any TASK commands");
                }
                this.taskArguments = temp[1];
                break;
            }
        } catch (NumberFormatException e) {
            throw new KiraException("Index is not a number...\nPlease check your arguments.");
        } catch (IllegalArgumentException e) {
            throw new KiraException("Sorry, I don't know this command :C");
        } 
    }

    public Task parseOutputTask() throws KiraException {
        TaskType type = TaskType.valueOf(this.command.toString());
        switch (type) {
        case TODO:
            return new ToDo(this.taskArguments);
        case DEADLINE:
            String[] format = this.taskArguments.split(" /by ", 2);
            if (format.length != 2) {
                throw new KiraException("Incorrect deadline format :C\n"
                        + "Please follow this format for deadline:\n"
                        + "deadline <description> /by yyyy-MM-dd HHmm");
            }
            return new Deadline(format[0], format[1]);
        case EVENT:
            String errMsg = "Incorrect event format :C\n"
                    + "Please follow this format for event:\n"
                    + "event <description> /from yyyy-MM-dd HHmm "
                    + "/to yyyy-MM-dd HHmm";
            format = this.taskArguments.split(" /from ", 2);
            if (format.length != 2) {
                throw new KiraException(errMsg);
            }
            String desc = format[0];
            format = format[1].split(" /to ", 2);
            if (format.length != 2) {
                throw new KiraException(errMsg);
            }
            return new Event(desc, format[0], format[1]);
        }
        // Should never reach here
        throw new KiraException("Unexpected error...");
    }

    public int getIndex() {
        return index;
    }
}
