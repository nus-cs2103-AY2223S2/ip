package duke;

import duke.commands.*;

/**
 * The class that process the command input by the client.
 */
public class Parser {

    /**
     * A static method that parse the input string.
     *
     * @return command objects based on different command keywords.
     */
    public static Command parse(String input) throws DukeException {
        String[] arr1 = input.split("/");
        String[] arr2 = arr1[0].split(" ");
        try {
            switch (arr2[0]) {
            case "list":
                return new ListCommand();

            case "mark":
                assert arr2.length <= 2;
                if (arr2.length < 2) {
                    throw new DukeException("Be more specific. The description of mark cannot be empty.");
                }
                int index = Integer.parseInt(arr2[1]);
                return new MarkCommand(index);

            case "unmark":
                assert arr2.length <= 2;
                if (arr2.length < 2) {
                    throw new DukeException("Be more specific. The description of unmark cannot be empty.");
                }
                int number = Integer.parseInt(arr2[1]);
                return new UnmarkCommand(number);

            case "find":
                if (arr2.length < 2) {
                    throw new DukeException("Be more specific. The description of find cannot be empty.");
                }
                String target = arr1[0].substring(arr1[0].indexOf(" ") + 1);
                return new FindCommand(target);

            case "delete":
                if (arr2.length < 2) {
                    throw new DukeException("Be more specific. The description of delete cannot be empty.");
                }
                int d = Integer.parseInt(arr2[1]);
                return new DeleteCommand(d);

            case "todo":
                if (arr2.length < 2) {
                    throw new DukeException("Be more specific. The description of todo cannot be empty.");
                }
                String name = arr1[0].substring(arr1[0].indexOf(" ") + 1);
                return new TodoCommand(name);

            case "deadline":
                if (arr2.length < 2) {
                    throw new DukeException("Be more specific. The description of deadline cannot be empty.");
                }
                String dName = arr1[0].substring(arr1[0].indexOf(" ") + 1, arr1[0].length() - 1);
                String dTime = arr1[1].substring(arr1[1].indexOf(" ") + 1);
                return new DeadlineCommand(dName, dTime);

            case "event":
                if (arr2.length < 2) {
                    throw new DukeException("Be more specific. The description of event cannot be empty.");
                }
                String eName = arr1[0].substring(arr1[0].indexOf(" ") + 1, arr1[0].length() - 1);
                String sTime = arr1[1].substring(arr1[1].indexOf(" ") + 1, arr1[1].length() - 1);
                String eTime = arr1[2].substring(arr1[2].indexOf(" ") + 1);
                return new EventCommand(eName, sTime, eTime);

            case "bye":
                return new ByeCommand();

            default:
                throw new DukeException("I don't understand. You should talk in my language, human.");
            }
        } catch (DukeException e) {
            return new ExceptionCommand(e.getMessage());
        }
    }
}
