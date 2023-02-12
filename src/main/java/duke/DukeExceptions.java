package duke;

/**
 * The class DukeExceptions helps to check whether or not an exception can be thrown
 * If an exception can be thrown, then DukeExceptions will throw Exceptions that are
 * specific to Duke
 */
public class DukeExceptions {
    /**
     * Checks whether a description is present when describing a task or action (Todo, Deadline, Event, Mark, Unmark),
     * If no description is present, then it throws anEmptyDescriptionException
     * @param command a String array that is split from the original command
     * @throws EmptyDescriptionException the exception thrown when no description is present
     */
    static void checkEmptyDescription(String[] command) throws EmptyDescriptionException {
        assert !command.equals(null);
        if (command.length == 1 || (command[0].equals("deadline") && command[1].equals("/by")) 
            || (command[0].equals("event") && (command[1].equals("/from") || command[1].equals("/to")))) {
            throw new EmptyDescriptionException("☹ OOPS!!! The description of a " + command[0] + " cannot be empty");
        }
    }
    /**
     * Checks whether the file data/duke.txt is present, if not then it throws a PastDataDoesNotExistException
     * @param fileExists boolean indicating whether a file exists
     * @throws PastDataDoesNotExistException the exception thrown when the file is not present
     */
    static void checkPastData(boolean fileExists) throws PastDataDoesNotExistException {
        if (!fileExists) {
            throw new PastDataDoesNotExistException("☹ OOPS!!! Past data does not exist!");
        }
    }
    /**
     * Checks whether it is a valid command, if not this method throws a DontKnowWhatThatMeansException
     * @param command a String array that is split from the original command
     * @throws DontKnowWhatThatMeansException the Exception thrown when it is not a valid command
     */
    static void checkCommand(String[] command) throws DontKnowWhatThatMeansException {
        assert !command.equals(null);
        String[] commandArray = {"mark", "unmark", "todo", "delete", "deadline", "event", "list", "bye", "find", "greet"};
        boolean check = false;
        for (int i = 0; i < commandArray.length; i++) {
            if (command[0].equals(commandArray[i])) {
                check = true;
            }
        }
        if (check == false) {
            throw new DontKnowWhatThatMeansException("☹ OOPS!!! Don't know what that means");
        }
    }
}
