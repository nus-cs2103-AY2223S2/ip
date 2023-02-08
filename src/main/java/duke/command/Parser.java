package duke.command;

import duke.DukeException;

/**
 * The type Parser.
 */
public class Parser {

    /**
     * The enum Keyword.
     */
    public enum Keyword {
        /**
         * Bye keyword.
         */
        bye,
        /**
         * List keyword.
         */
        list,
        /**
         * Mark keyword.
         */
        mark,
        /**
         * Unmark keyword.
         */
        unmark,
        /**
         * Todo keyword.
         */
        todo,
        /**
         * Deadline keyword.
         */
        deadline,
        /**
         * Event keyword.
         */
        event,
        /**
         * Delete keyword.
         */
        delete,
        /**
         * Find keyword.
         */
        find
    }

    /**
     * Parse user input and output intended command
     *
     * @param input the input
     * @return the command
     */
    public static Command parse(String input) {
        String[] parsedInput = input.split(" ", 2);
        try {
            Keyword command = Keyword.valueOf(parsedInput[0]);
            switch (command) {
            case bye:
                return new ExitCommand();
            case list:
                return new ListCommand();
            case mark:
                //extract task number to mark from input
                int indexToMark = processMarkUnmarkDel(input);
                return new MarkCommand(indexToMark, true);
            case unmark:
                //extract task number to unmark from input
                int indexToUnmark = processMarkUnmarkDel(input);
                return new MarkCommand(indexToUnmark, false);
            case todo:
                return new AddCommand(parsedInput[1]);
            case deadline:
                String[] parsedDeadline = processDeadline(input);
                return new AddCommand(parsedDeadline[0], parsedDeadline[1]);
            case event:
                String[] parsedEvent = processEvent(input);
                return new AddCommand(parsedEvent[0], parsedEvent[1],
                        parsedEvent[2]);
            case delete:
                //extract task number to delete from input
                int indexToDelete = processMarkUnmarkDel(input);
                return new DeleteCommand(indexToDelete);
            case find:
                return new FindCommand(parsedInput[1]);
            default:
                throw new DukeException("unknown command detected");
            }
        } catch (Exception e) {
            return new UnknownCommand();
        }
    }

    /**
     * Parse task string [ ].
     *
     * @param taskString the task string
     * @return the string [ ]
     */
    public static String[] parseTask(String taskString) {
        return taskString.split(" \\| ");
    }

    /**
     * Process input that has a task number and output that task number
     *
     * @param input the input
     * @return the int
     * @throws DukeException the duke exception
     */
    public static int processMarkUnmarkDel(String input) throws DukeException {
        String[] parsedInput = input.split(" ");
        if (parsedInput.length != 2) {
            throw new DukeException("index of task to delete is missing");
        }
        try {
            int index = Integer.parseInt(parsedInput[1]);
            return index - 1;
        } catch (NumberFormatException n) {
            throw new DukeException(n.getMessage());
        }
    }

    /**
     * Process deadline string [ ].
     *
     * @param input the input
     * @return the string [ ]
     * @throws DukeException the duke exception
     */
    public static String[] processDeadline(String input) throws DukeException {
        //get deadline details from input
        String deadlineDetails = input.split("deadline", 2)[1];
        if (deadlineDetails.equals("")) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }

        //get the date of the deadline from the deadline details
        String[] parsedDeadline = deadlineDetails.split("/by ", 2);
        if (parsedDeadline.length < 2) {
            throw new DukeException("When the deadline should be completed by should be specified using /by.");
        }
        /*ensure the date of deadline matches the format of a Local Date object so
            that it can be made into a deadline object*/
        if (!parsedDeadline[1].matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new DukeException("Please specify deadline in the format '{description} /by {yyyy-mm-dd}");
        }

        return parsedDeadline;
    }

    /**
     * Process event string [ ].
     *
     * @param input the input
     * @return the string [ ]
     * @throws DukeException the duke exception
     */
    public static String[] processEvent(String input) throws DukeException {
        //get event details from input
        String eventDetails = input.split("event", 2)[1];
        if (eventDetails.equals("")) {
            throw new DukeException("The description of a event cannot be empty.");
        }

        //get the time from which the event starts
        String[] parsed1 = eventDetails.split("/from ", 2);
        if (parsed1.length < 2) {
            throw new DukeException("The event's timeline should be specified using /from and /to.");
        }
        String description = parsed1[0];

        //get the time when the event ends
        String[] parsed2 = parsed1[1].split("/to ", 2);
        if (parsed2.length < 2) {
            throw new DukeException("The event's timeline should be specified using /from and /to.");
        }
        String from = parsed2[0];
        String to = parsed2[1];

        return new String[]{description, from, to};
    }
}
