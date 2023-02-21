package duke;

import duke.exceptions.DukeyException;

import java.time.LocalDate;

public class Parser {

    public boolean checkIfEmpty(String input) {
        return input.strip().equals("");
    }

    public ActionEnum parseCommand(String input) throws DukeyException {
        input = input.strip().toLowerCase();
        if (checkIfEmpty(input)) {
            throw new DukeyException("Error! Input cannot be empty.");
        }
        if (input.equals("todo")) {
            return ActionEnum.TODO;
        }
        if (input.equals("deadline")) {
            return ActionEnum.DEADLINE;
        }
        if (input.equals("event")) {
            return ActionEnum.EVENT;
        }
        if (input.equals("list")) {
            return ActionEnum.LIST;
        }
        if (input.equals("mark")) {
            return ActionEnum.MARK;
        }
        if (input.equals("unmark")) {
            return ActionEnum.UNMARK;
        }
        if (input.equals("delete")) {
            return ActionEnum.DELETE;
        }
        if (input.equals("save")) {
            return ActionEnum.SAVE;
        }
        if (input.equals("clearlist")) {
            return ActionEnum.CLEARLIST;
        }
        if (input.equals("clearsave")) {
            return ActionEnum.CLEARSAVE;
        }
        if (input.equals("bye")) {
            return ActionEnum.BYE;
        }
        if (input.equals("forcestop")) {
            return ActionEnum.FORCESTOP;
        }
        if (input.equals("find")) {
            return ActionEnum.FIND;
        }

        throw new DukeyException("Error! Invalid command!");
    }

    public String parseTaskName(String input) throws DukeyException{
        input = input.strip();
        if (checkIfEmpty(input)) {
            throw new DukeyException("Error! Task name cannot be empty!");
        }
        return input;
    }

    public LocalDate parseDate(String input) throws DukeyException {
        input = input.strip();
        if (checkIfEmpty(input)) {
            throw new DukeyException("Input cannot be empty!");
        }
        return DukeyTime.getDateFromString(input);
    }

    public String parseKeyword(String input) throws DukeyException {
        input = input.strip();
        if (checkIfEmpty(input)) {
            throw new DukeyException("Input cannot be empty!");
        }
        return input;
    }
}
