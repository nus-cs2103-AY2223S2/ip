public class Parser {

    public static Command parseCommand(String command) throws DukeException {
        if (!Command.contains(command)) {
            throw new DukeException("Sorry! I don't know what that means!");
        }
        return Command.valueOf(command);
    }

    public static String[] parseStartingElements(String elemString) {
        return elemString.split(" ");
    }

    public static String parseTodo(String str) {
        return str.substring(4);
    }

    public static String[] parseDeadline(String str) {
        String temp = str.substring(8);

        return temp.split("/by");
    }

    public static String[] parseEvent(String str) throws DukeException {
        String temp = str.substring(5);
        String[] arr1 = temp.split("/from");
        if (arr1.length != 2) {
            throw new DukeException("I don't know what that means. Format it as 'event [do something] /from [start date] /to [end date]'");
        }
        String[] arr2 = arr1[1].split("/to");
        if (arr2.length != 2) {
            throw new DukeException("I don't know what that means. Format it as 'event [do something] /from [start date] /to [end date]'");
        }
        return new String[] {arr1[0], arr2[0], arr2[1]};
    }
}
