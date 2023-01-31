package duke.parser;

import duke.commands.*;
import duke.exception.BlankFieldExceptions.BlankFieldDeadlineException;
import duke.exception.BlankFieldExceptions.BlankFieldEventException;
import duke.exception.BlankFieldExceptions.BlankFieldTodoException;
import duke.exception.TaskNumberNotFoundException;
import duke.exception.UnknownCommandError;

import java.util.Objects;

public class Parser {

    private static final String FRAME = "    ____________________________________________________________\n";

    public Parser() {

    }

    public Command parse(String command, int lengthOfList) throws BlankFieldEventException, BlankFieldDeadlineException, BlankFieldTodoException, TaskNumberNotFoundException, UnknownCommandError {
        if (Objects.equals(command, "list")) {
            return new ListCommand();
        } else if (Objects.equals(command, "bye")) {
            return new ByeCommand();
        }


        String[] words = command.split(" ", 2);

        if (words.length <= 1) {
            throw new BlankFieldEventException();
        }

        String keyWord = words[0];
        String commandBody = words[1];
        switch (keyWord) {
        case "mark":
            try {
                int taskNumber = Integer.parseInt(words[1]);
                if (taskNumber > lengthOfList || taskNumber <= 0 || commandBody.trim().isEmpty()) {
                    throw new TaskNumberNotFoundException();
                }
                return new MarkCommand(taskNumber);
            } catch (NumberFormatException e) {
                throw new TaskNumberNotFoundException();
            }
        case "unmark":

            try {
                int taskNumber = Integer.parseInt(words[1]);
                if (taskNumber > lengthOfList || taskNumber <= 0 || commandBody.trim().isEmpty()) {
                    throw new TaskNumberNotFoundException();
                }
                return new UnmarkCommand(taskNumber);
            } catch (NumberFormatException e) {
                throw new TaskNumberNotFoundException();
            }

        case "todo":


            if (commandBody.trim().isEmpty()) {
                throw new BlankFieldTodoException();
            }
            return new AddToDoCommand(commandBody);

        case "deadline":


            if (words[1].trim().isEmpty()) {
                throw new BlankFieldDeadlineException();
            }
            return new AddDeadlineCommand(commandBody);

        case "event":

            if (words[1].trim().isEmpty()) {
                throw new BlankFieldEventException();
            }
            return new AddEventCommand(commandBody);
        case "delete":



            try {
                int taskNumber = Integer.parseInt(words[1]);
                if (taskNumber > lengthOfList || taskNumber <= 0 || words[1].trim().isEmpty()) {
                    throw new TaskNumberNotFoundException();
                }
                return new DeleteCommand(taskNumber);
            } catch (NumberFormatException e) {
                throw new TaskNumberNotFoundException();
            }
        }

        throw new UnknownCommandError("\n" + FRAME + "\n" +
                "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(" + "\n" +
                FRAME);
    }

}
