package duke;

import java.time.LocalDateTime;

public class Parser {

    /**
     * Parses input by the user and returns the corresponding Command object.
     *
     * @param command String input by the user.
     * @return Command object to be executed.
     * @throws DukeException If command cannot be parsed into a known command.
     */
    public static Command parse(String command) throws DukeException {
        if (command.equals("list")) {
            return new ListCommand();
        } else if (command.startsWith("stats")) {
          return new StatsCommand(LocalDateTime.now());
        } else if (command.startsWith("mark")) {
            int taskNumber = Integer.parseInt(command.split(" ")[1]);
            return new MarkCommand(taskNumber, LocalDateTime.now());
        } else if (command.startsWith("unmark")) {
            int taskNumber = Integer.parseInt(command.split(" ")[1]);
            return new UnmarkCommand(taskNumber, LocalDateTime.now());
        } else if (command.startsWith("deleteAll")) {
            return new DeleteAllCommand(command.split(" ")[1]);
        } else if (command.startsWith("delete")) {
            int taskNumber = Integer.parseInt(command.split(" ")[1]);
            return new DeleteCommand(taskNumber);
        } else if (command.startsWith("find")) {
            return new FindCommand(command.split(" ")[1]);
        } else {
            String type = command.split(" ")[0];
            String[] commandArr = command.split(" ", 2);

            switch (type) {
            case "todo":
                if (commandArr.length == 1) {
                    throw new DukeException("How are you gonna do an empty todo?");
                }

                String todoDescription = commandArr[1];
                return new TodoCommand(todoDescription);
            case "deadline":
                if (commandArr.length == 1) {
                    throw new DukeException("Deadline for...?");
                }

                String[] deadlineArr = commandArr[1].split(" /by ");

                if (deadlineArr.length == 1) {
                    throw new DukeException("What's the deadline for your task??");
                }

                return new DeadlineCommand(deadlineArr[0], deadlineArr[1]);
            case "event":
                if (commandArr.length == 1) {
                    throw new DukeException("What event is this??");
                }

                String[] eventArr = commandArr[1].split(" /from ");

                if (eventArr.length == 1) {
                    throw new DukeException("When does your event start??");
                }

                String[] fromToArr = eventArr[1].split(" /to ");

                if (fromToArr.length == 1) {
                    throw new DukeException("When does your event end??");
                }

                return new EventCommand(eventArr[0], fromToArr[0], fromToArr[1]);
            default:
                throw new DukeException("Sorry I don't understand what you're talking about.");
            }
        }
    }
}
