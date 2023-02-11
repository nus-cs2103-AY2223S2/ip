package babe;

import babe.exception.NoDescriptionException;
import babe.exception.NonsenseInputException;
import babe.exception.WrongDateFormatException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The <code>Parser</code> class deals with making sense of the user command.
 * It contains the method parse(String), which is responsible for processing the user input,
 * distinguishing the commands and the parameters.
 */
class Parser {

    /**
     * A string input from user.
     */
    private static ArrayList<String> userInput = new ArrayList<>();

    /**
     * Length of user input.
     */
    private static int userInputLen = 0;

    /**
     * Rebuilds a string from ArrayList from the starting index to the ending index (not inclusive).
     * A helper function to recover the original user input from userInput starting from the startingIndex
     * to the ending index (not inclusive).
     */
    private static String rebuildUserInput(int startingIndex, int endingIndex) {
        String result = "";
        for (int i = startingIndex; i < endingIndex; i++) {
            result += userInput.get(i);
            result += " ";
        }
        return result.stripTrailing();
    }

    /**
     * Finds and returns index of command arguments in userInput demarcated by given String pattern
     *
     * @return An integer that is the index of command argument.
     * @args pattern A String pattern that precedes the input of command argument
     */
    private static int findArgument(String pattern) {

        int argIndex = -1;

        for (int i = 0; i < userInput.size(); i++) {
            String currentString = userInput.get(i);
            if (currentString.equals(pattern)) {
                argIndex = i + 1;
                break;
            }
        }

        return argIndex;
    }

    /**
     * Formats date specified by user.
     * The date will be formatted using java.LocalDate.
     *
     * @param dateAndTime A String that contains date and time specified by the user.
     * @return A String containing the formatted date and original specified time.
     */
    private static String formatDate(String dateAndTime) {
        String[] words = dateAndTime.split(" ");

        LocalDate d1 = LocalDate.parse(words[0]);
        String formattedDateAndTime = Integer.toString(d1.getDayOfMonth()) + " "
                + d1.getMonth() + " " + d1.getYear() + " "
                + (words.length == 2 ? words[1] : "");

        return formattedDateAndTime;

    }

    /**
     * Returns true if the description is available for an instruction.
     * Throws NoDescriptionException if description is not available.
     * @return A boolean value;
     */
    private static void checkIfDescriptionAvail(int len)
            throws NoDescriptionException {
        if (len == 1) {
            throw new NoDescriptionException();
        }
    }


    public static ArrayList<String> parse(String input)
            throws NoDescriptionException, NonsenseInputException, WrongDateFormatException {

        userInput = new ArrayList<>(Arrays.asList(input.split(" ")));
        int inputLength = userInput.size();

        String instruction = userInput.get(0).toLowerCase();


        ArrayList<String> outputs = new ArrayList<>();
        outputs.add(instruction);

        if (instruction.equals("bye") && inputLength == 1) {

        } else if (instruction.equals("list") && inputLength == 1) {

        } else if (instruction.equals("mark")) {
            outputs.add(userInput.get(1));

        } else if (instruction.equals("unmark")) {
            outputs.add(userInput.get(1));

        } else if (instruction.equals("todo")) {
            checkIfDescriptionAvail(inputLength);
            String description = Parser.rebuildUserInput(1, inputLength);
            outputs.add(description);

        } else if (instruction.equals("deadline")) {
            checkIfDescriptionAvail(inputLength);

            int deadlineIndex = Parser.findArgument("/by");
            String description = Parser.rebuildUserInput(1, deadlineIndex - 1);
            String deadline = Parser.formatDate(Parser.rebuildUserInput(deadlineIndex, inputLength));

            try {
                outputs.add(description);
                outputs.add(deadline);
            } catch (Exception e) {
                throw new WrongDateFormatException();
            }

        } else if (instruction.equals("event")) {
            checkIfDescriptionAvail(inputLength);

            int startDateIndex = Parser.findArgument("/from");
            int endDateIndex = Parser.findArgument("/to");

            String description = Parser.rebuildUserInput(1, startDateIndex - 1);
            String startDate = Parser.formatDate(Parser.rebuildUserInput(startDateIndex, endDateIndex - 1));
            String endDate = Parser.formatDate(Parser.rebuildUserInput(endDateIndex, inputLength));

            try {
                outputs.add(description);
                outputs.add(startDate);
                outputs.add(endDate);
            } catch (Exception e) {
                throw new WrongDateFormatException();
            }

        } else if (instruction.equals("delete")) {
            checkIfDescriptionAvail(inputLength);
            outputs.add(userInput.get(1));

        } else if (instruction.equals("find")) {
            checkIfDescriptionAvail(inputLength);
            outputs.add(rebuildUserInput(1, inputLength));

        } else {
            throw new NonsenseInputException();
        }

        return outputs;

    }


}
