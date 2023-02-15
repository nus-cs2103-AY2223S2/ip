package panav.command;


import panav.exception.InvalidNumberException;

/**
 * Class to encapsulate a 'mark' or 'unmark' command, extends from Command.
 */
public abstract class EditCommand extends Command {


    /**
     * Returns the index number for commands which manipulate the list.
     *
     * @param command The command which is manipulating list.
     * @param taskListLength The number of elements in the list.
     * @return Index number in command.
     * @throws InvalidNumberException If the index doesn't exist.
     */
    public int readNumber(String command, int taskListLength) throws InvalidNumberException {
        int len = command.length();
        char charNum = command.charAt(len - 1);
        String stringNum = String.valueOf(charNum);
        int number = Integer.parseInt(stringNum);
        if (number > taskListLength || number < 1) {
            throw new InvalidNumberException("Oops!! There is no such index number in your list");
        } else {
            return number;
        }
    }


}
