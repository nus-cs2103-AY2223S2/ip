package duke;

import duke.command.*;
import duke.command.DeleteCommand;

import duke.exceptions.InvalidArgumentException;
import duke.exceptions.EmptyCommandException;
import duke.exceptions.InvalidCommandException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    /**
     * Returns a Command instance after parsing the String.
     *
     * @param fullCommand String version of the command
     * @return Command instance
     * @throws EmptyCommandException If param fullCommand is empty.
     * @throws InvalidCommandException If param fullCommand's first word is not a valid instruction.
     * @throws InvalidArgumentException If param fullCommand's first word is not followed by a valid argument.
     */
    public static Command parse(String fullCommand) throws EmptyCommandException, InvalidCommandException, InvalidArgumentException {
        if (fullCommand.equals("")) {
            throw new EmptyCommandException();
        }

        switch (fullCommand) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        }

        String[] s = fullCommand.split(" ", 2);
        String firstWord = s[0];
        ArrayList<String> strCommandList = new ArrayList<>(
                List.of("mark", "unmark", "delete", "todo", "find", "deadline", "event"));

        if (!strCommandList.contains(firstWord)) {
            throw new InvalidCommandException();
        }

        if (s.length <= 1) { throw new InvalidArgumentException();}

        assert(s.length == 2);
        assert(strCommandList.contains(firstWord));

        switch (firstWord) { // add assert here
        case "mark":
            return new MarkCommand(1, Integer.parseInt(s[1]) - 1);
        case "unmark":
            return new MarkCommand(0, Integer.parseInt(s[1]) - 1);
        case "delete":
            return new DeleteCommand(Integer.parseInt(s[1]) - 1);
        case "todo":
            return new AddCommand(TaskType.ToDo, s[1], false);
        case "find":
            return new FindCommand(s[1]);
        case "deadline":
            String[] secondSplitArray = s[1].split(" /by ", 2);
            return new AddCommand(TaskType.Deadline, secondSplitArray[0], false, LocalDate.parse(secondSplitArray[1]));
        case "event":
            String[] thirdSplitArray = s[1].split(" /from ", 2);
            String[] fourthSplitArray = thirdSplitArray[1].split(" /to ", 2);
            return new AddCommand(TaskType.Event, thirdSplitArray[0], false, fourthSplitArray[0], fourthSplitArray[1]);
        default:
            throw new InvalidCommandException();
        }

    }

}
