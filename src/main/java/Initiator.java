/**
 * The initiator of Duke. Runs appropriate commands with user inputs
 */
public class Initiator {
    /**
     * Takes in a line of user input.
     * If command is valid, return command accordingly.
     * Throws DukeException if command is invalid.
     *
     * @param commands The commands from the user.
     * @return appropriate commands.
     * @throw DukeException if command is invalid.
     */
    public static Object InitiatorCommand(String commands) throws DukeException {
        if (commands == "list") {
            return new List();
        } else if (commands == "mark") {
            return new Done();
        } else if (commands == "todo") {
            return new Todo();
        } else if (commands == "event") {
            return new Event();
        } else if (commands == "deadline") {
            return new Deadline();
        } else if (commands == "bye") {
            return new Exit();
        } else if (commands == "delete") {
            return new Delete();
        } else {
            throw new DukeException("I am sorry but I failed to comprehend your command.");
        }
        return null;
    }
}
