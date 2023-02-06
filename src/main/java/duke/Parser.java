package duke;

import duke.command.*;
import duke.command.DeleteCommand;

import duke.exceptions.InvalidArgumentException;
import duke.exceptions.EmptyCommandException;
import duke.exceptions.InvalidCommandException;

import java.time.LocalDate;

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

        String s[] = fullCommand.split(" ", 2);
        String firstWord = s[0];

        if (firstWord.equals("mark") || firstWord.equals("unmark") || firstWord.equals("delete") || firstWord.equals("todo") ||
                firstWord.equals("deadline") || firstWord.equals("event") || firstWord.equals("find")) {
            assert(s.length > 0 && s.length <= 2);
            if (s.length <= 1) { throw new InvalidArgumentException();} //might wanna chk for firstWord first

            assert (firstWord.equals("mark") || firstWord.equals("unmark") || firstWord.equals("delete") || firstWord.equals("todo") ||
                    firstWord.equals("deadline") || firstWord.equals("event") || firstWord.equals("find"));

            switch (firstWord) {
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
                String st[] = s[1].split(" /by ", 2);
                return new AddCommand(TaskType.Deadline, st[0], false, LocalDate.parse(st[1]));
            case "event":
                String stt[] = s[1].split(" /from ", 2);
                String sttt[] = stt[1].split(" /to ", 2);
                return new AddCommand(TaskType.Event, stt[0], false, sttt[0], sttt[1]);
            }
            throw new InvalidCommandException(); // extra

        } else {
            throw new InvalidCommandException();
        }

    }

}
