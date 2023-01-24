import Exceptions.EmptyArgumentException;
import Exceptions.EmptyCommandException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;

import java.io.IOException;
import java.time.LocalDate;

public class Parser {

    public static Command parse(String fullCommand) {
        if (fullCommand.equals("")) {
            throw new EmptyCommandException();
        }

        String s[] = fullCommand.split(" ", 2);
        String firstWord = s[0];


        switch (firstWord) {
            case "bye":
                return new ExitCommand();
            break;
            case "list":
                return new ListCommand();
            break;
            case "unmark":
                break;
            case "delete":
                return new DeleteCommand(Integer.parseInt(s[1]) - 1);
            break;
            case "todo":
                return new AddCommand(TaskType.ToDo, s[1], false);
            break;
            case "deadline":
                String st[] = s[1].split(" /by ", 2);
                return new AddCommand(TaskType.Deadline, st[0], false, LocalDate.parse(st[1]));
            break;
            case "event":
                String st[] = s[1].split(" /from ", 2);
                String stt[] = st[1].split(" /to ", 2);
                return new AddCommand(TaskType.Event, st[0], false, stt[0], stt[1]);
            break;
        }
    }

}
