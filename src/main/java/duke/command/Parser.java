package duke.command;

import duke.DukeException;

public class Parser {

    public enum Keywords {
        bye, list, mark, unmark, todo, deadline, event, delete, err
    }

    public static Command parse(String input) {
        String[] parsedInput = input.split(" ");
        Keywords command;
        try {
            command = Keywords.valueOf(parsedInput[0]);
            switch (command) {
                case bye:
                    return new ExitCommand();
                case list:
                    return new ListCommand();
                case mark:
                    return new MarkCommand(processMarkUnmarkDel(input), true);
                case unmark:
                    return new MarkCommand(processMarkUnmarkDel(input), false);
                case todo:
                    return new AddCommand(parsedInput[1]);
                case deadline:
                    String parsedDeadline[] = processDeadline(input);
                    return new AddCommand(parsedDeadline[0], parsedDeadline[1]);
                case event:
                    String parsedEvent[] = processEvent(input);
                    return new AddCommand(parsedEvent[0], parsedEvent[1], parsedEvent[2]);
                case delete:
                    return new DeleteCommand(processMarkUnmarkDel(input));
            }
        } catch (Exception e) {
            return new UnknownCommand();
        }
        return new UnknownCommand();
    }

    public static String[] parseTask(String taskString) {
        return taskString.split(" \\| ");
    }

    public static int processMarkUnmarkDel(String input) throws DukeException {
        String[] parsedInput = input.split(" ");
        if (parsedInput.length != 2) {
            throw new DukeException("index of task to delete is missing");
        }
        try {
            int index = Integer.parseInt(parsedInput[1]);
            return index - 1;
        } catch(NumberFormatException n) {
            throw new DukeException(n.getMessage());
        }
    }

    public static String[] processDeadline(String input) throws DukeException{
        String raw = input.split("deadline", 2)[1];
        if (raw.equals("")) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }

        String[] parsed = raw.split("/by ", 2);
        if(parsed.length < 2){
            throw new DukeException("When the deadline should be completed by should be specified using /by.");
        }
        if(!parsed[1].matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new DukeException("Please specify deadline in the format '{description} /by {yyyy-mm-dd}");
        }
        return parsed;
    }

    public static String[] processEvent(String input) throws DukeException{
        String raw = input.split("event", 2)[1];
        if (raw.equals("")) {
            throw new DukeException("The description of a event cannot be empty.");
        }
        String[] parsed1 = raw.split("/from ", 2);
        if(parsed1.length < 2){
            throw new DukeException("The event's timeline should be specified using /from and /to.");
        }
        String[] parsed2 = parsed1[1].split("/to ", 2);
        if(parsed2.length < 2){
            throw new DukeException("The event's timeline should be specified using /from and /to.");
        }
        return new String[]{parsed1[0], parsed2[0], parsed2[1]};
    }
}
