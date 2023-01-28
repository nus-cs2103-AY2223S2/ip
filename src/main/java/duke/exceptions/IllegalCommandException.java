package duke.exceptions;

import duke.commands.Commands;

/**
 * Represents an exception thrown by Duke due to an unrecognized command found in the user's input.
 */
public class IllegalCommandException extends DukeException {

    private Commands c;

    public IllegalCommandException(Commands c) {
        this.c = c;
    }

    /**
     * Returns a string describing the IllegalCommandException, depending on the unrecognized command type.
     *
     * @return String representation of the IllegalCommandException.
     */
    @Override
    public String toString() {
        switch (this.c) {
        case LIST:
            return "Command Exception: Make sure that your list command is as such\n\nlist\n";
        case BYE:
            return "Command Exception: Make sure that your list command is as such\n\nbye\n\n"
                    + "Having troubles saying bye huh ;)\n";
        case MARK:
            return "Command Exception: Make sure that your mark command is as such\n\nmark index-of-task\n";
        case UNMARK:
            return "Command Exception: Make sure that your unmark command is as such\n\nunmark index-of-task\n";
        case TODO:
            return "Command Exception: Make sure that your todo command is as such\n\ntodo name-of-task\n";
        case EVENT:
            return "Command Exception: Make sure that your event command is as such\n\nevent name-of-task "
                    + "/from start-date (format: yyyy-MM-dd HH:mm) /to end-date (format: yyyy-MM-dd HH:mm)\n";
        case DEADLINE:
            return "Command Exception: Make sure that your deadline command is as such\n\ndeadline name-of-task "
                    + "/by date-of-deadline (format: yyyy-MM-dd HH:mm)\n";
        case DELETE:
            return "Command Exception: Make sure that your delete command is as such\n\ndelete index-of-task\n";
        case FIND:
            return "Command Exception: Make sure that your find command is as such\n\nfind enter-keyword-here\n";
        case UNRECOGNIZED:
            return "Command Exception: That is not a recognized command!\n";
        default:
            return super.toString();
        }
    }
}
