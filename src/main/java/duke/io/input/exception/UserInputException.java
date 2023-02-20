package duke.io.input.exception;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import duke.util.Parser;

/**
 * An implementation of the {@code DukeException} template. It is used
 * to detect errors within user's input, and throw the corresponding
 * error message
 *
 */

public class UserInputException extends Exception {

    /**
     * Makes sense of the user's input. Detects incorrect inputs and
     * OutOfBound errors. Throw corresponding error message when such error
     * is detected
     *
     * @param input the user's newest input
     * @param currentSize the currentSize of the list of tasks, which is also
     *                    the current number of tasks
     * @throws DukeException when input has incorrect format, and when index of list of tasks
     *                       is OutOfBound
     */

    public static void checkUserInput(String input, int currentSize) throws DukeException {
        String[] inputSplitArray = input.split(" ");
        List<String> inputSplitList = Arrays.asList(inputSplitArray);
        int lengthOfCommand = inputSplitList.size();

        String command = inputSplitList.get(0);
        if (command.equals("TODO") || command.equals("DEADLINE") || command.equals("EVENT")) {
            checkParsingTask(input, lengthOfCommand);
        } else if (command.equals("MARK") || command.equals("UNMARK") || command.equals("DELETE")) {
            int requestedIndex = Integer.valueOf(inputSplitList.get(1)) - 1;
            checkMarkingAndDelete(requestedIndex, lengthOfCommand, currentSize);
        } else if (command.equals("LIST")) {
            if (lengthOfCommand > 1) {
                throw new DukeException("ARE YOU EXPECTING ME TO DO SOMETHING WITH THE EXTRA INFO?");
            }
        } else if (command.equals("FIND")) {
            if (lengthOfCommand > 2) {
                throw new DukeException("THIS PLACE... IT MADE ME WEAK! I CAN ONLY SEARCH 1 KEYWORD NOW");
            } else if (lengthOfCommand == 1) {
                throw new DukeException("WHAT EXACTLY DO YOU WANT TO FIND...");
            }
        } else if (command.equals("SCHEDULE")) {
            checkSchedule(inputSplitList.get(1));
        } else {
            throw new DukeException("COMMAND NOT FOUND... WHAT ARE YOU UP TO...");
        }
    }

    private static void checkParsingTask(String input, int lengthOfCommand) throws DukeException {
        String command = input.split(" ")[0];
        if (command.equals("TODO")) {
            checkToDo(input, lengthOfCommand);
        } else if (command.equals("DEADLINE")) {
            checkDeadline(input, lengthOfCommand);
        } else {
            checkEvent(input, lengthOfCommand);
        }
        try {
            Parser.parseTask(input);
        } catch (Exception e) {
            throw new DukeException("UH OH..." + e.getMessage());
        }
    }

    private static void checkToDo(String input, int lengthOfCommand) throws DukeException {
        if (lengthOfCommand <= 1) {
            throw new DukeException("EMPTY TO-DO COMMAND... DOING NOTHING MUCH?");
        }
    }

    private static void checkDeadline(String input, int lengthOfCommand) throws DukeException {
        if (input.contains(" /BY ")) {
            if (lengthOfCommand <= 3) {
                throw new DukeException("MORE INFO NEEDED FOR DEADLINE");
            }
        } else {
            throw new DukeException("NO DEADLINE. FREE TIME ISN'T SO EASY TO COME BY.");
        }
    }

    private static void checkEvent(String input, int lengthOfCommand) throws DukeException {
        if (input.contains(" /FROM ") && input.contains(" /TO ")) {
            if (lengthOfCommand <= 5) {
                throw new DukeException("MORE INFO NEEDED FOR EVENT");
            }
        } else {
            throw new DukeException("MY CREATOR DIDN'T MAKE ME SMART ENOUGH TO INFER INFO FROM THIS...");
        }
    }

    private static void checkMarkingAndDelete(int requestedIndex,
                                              int lengthOfCommand,
                                              int currentSize) throws DukeException {
        if (lengthOfCommand == 1) {
            throw new DukeException("MY CREATOR DIDN'T MAKE ME SMART ENOUGH TO INFER INFO FROM THIS...");
        } else if (lengthOfCommand > 2) {
            throw new DukeException("TOO MUCH INFO...");
        } else {
            if (requestedIndex > currentSize) {
                if (currentSize > 1) {
                    throw new DukeException("YOU ONLY HAVE " + currentSize + " TASKS");
                } else {
                    throw new DukeException("YOU ONLY HAVE " + currentSize + " TASK");
                }
            } else if (requestedIndex < 0) {
                throw new DukeException("BAD AT MATH? THAT INDEX DOESN'T EXIST");
            }
        }
    }

    private static void checkSchedule(String dateAsString) throws DukeException {
        try {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate scheduleDate = LocalDate.parse(dateAsString, dateFormat);
        } catch (Exception e) {
            throw new DukeException("WRONG DATE FORMAT... HOW FRUSTRATING");
        }
    }
}


