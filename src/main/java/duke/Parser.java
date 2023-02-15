package duke;

//CHECKSTYLE:OFF
import duke.exceptions.*;
//CHECKSTYLE:ON

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/** A class Parser that deals with making sense of the user command */
public class Parser {

    /**
     * Returns an array that contains the name of the task and the rest of the command after parsing
     *
     * @param line The command
     * @throws MissingTimeException If there is not a time element in the command
     * @throws MissingNameException If the name of the task is blank
     * @returns Array of string with name and the rest of the command
     */
    public static String[] separate(String line) throws MissingTimeException, MissingNameException {
        try {
            StringBuilder sb = new StringBuilder();

            int i;
            for (i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '/') {
                    break;
                }
                sb.append(line.charAt(i));
            }

            String name = sb.toString();
            if (name.isBlank()) {
                throw new MissingNameException();
            }
            String theRest = line.substring(i + 1);
            return new String[] {name, theRest};
        } catch (IndexOutOfBoundsException err) {
            throw new MissingTimeException();
        }
    }

    /**
     * Returns the deadline in the command
     *
     * @param line The command
     * @throws MissingTimeException If there is not a time element in the command
     * @throws InvalidSyntaxException If the command does not start with "by" keyword
     * @returns The deadline as string
     */
    public static String getDeadline(String line) throws MissingTimeException, InvalidSyntaxException {
        try {
            if (!line.startsWith("by ")) {
                throw new InvalidSyntaxException();
            }
            String toReturn = line.substring("by ".length());
            if (toReturn.isBlank()) {
                throw new MissingTimeException();
            }
            return toReturn;
        } catch (IndexOutOfBoundsException err) {
            throw new MissingTimeException();
        }
    }

    /**
     * Returns the task number in the command
     *
     * @param str The string passed
     * @param from The starting index to start searching for task number
     * @throws MissingArgumentsException If there is no number detected
     * @throws InvalidTaskNumberException If the task number is invalid: smaller or equal to
     *                                    0 or larger than list size
     * @returns The task number
     */
    public static int getNumber(String str, int from) throws MissingArgumentsException, InvalidTaskNumberException {
        int numOfTask;
        try {
            numOfTask = Integer.parseInt(str.substring(from));
        } catch (NullPointerException | IndexOutOfBoundsException err) {
            throw new MissingArgumentsException();
        }

        // Handle when the index is out of bound -> create a separate function
        if (numOfTask <= 0 || numOfTask > TaskList.getList().size()) {
            throw new InvalidTaskNumberException();
        }

        assert numOfTask > 0 && numOfTask <= TaskList.getList().size(): "Task number should be positive";

        return numOfTask;
    }

    /**
     * Returns the array that contains starting and ending time in the command
     *
     * @param line The string passed
     * @throws MissingArgumentsException If there is not either starting or ending time
     * @throws InvalidSyntaxException If the syntax of the command is not correct
     * @throws MissingTimeException If either of the starting or ending time is blank
     * @returns The task number
     */
    public static String[] getDuration(String line) throws MissingArgumentsException, 
                                                            MissingTimeException, 
                                                            InvalidSyntaxException {
        try {
            String[] arr = line.split("/");
            if (!arr[0].startsWith("from ")) {
                throw new InvalidSyntaxException();
            }
            if (!arr[1].startsWith("to ")) {
                throw new InvalidSyntaxException();
            }

            arr[0] = arr[0].substring("from ".length()).trim();
            arr[1] = arr[1].substring("to ".length()).trim();
            if (arr[0].isBlank() || arr[1].isBlank()) {
                throw new MissingTimeException();
            }
            return arr;
        } catch (IndexOutOfBoundsException err) {
            throw new MissingArgumentsException();
        }
    }

    /**
     * Returns the array that contains starting and ending time in the command
     *
     * @param str The string passed
     * @param from The index that starts searching from
     * @throws MissingNameException If name found is blank
     * @throws MissingArgumentsException If there is no name detected
     * @returns The name in the command
     */
    public static String getName(String str, int from) throws MissingNameException, MissingArgumentsException {
        try {
            String name = str.substring(from);
            if (name.isBlank()) {
                throw new MissingNameException();
            }
            return name;
        }  catch (IndexOutOfBoundsException err) {
            throw new MissingArgumentsException();
        }
    }

    /**
     * Returns the date as string in format of "MMM dd YYYY"
     *
     * @param d LocalDate object
     * @returns The date parsed
     */
    public static String getParsedDate(LocalDate d) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd YYYY");
            return d.format(formatter);
        } catch (DateTimeException err) {
            System.out.println(err.getMessage());
            return "";
        }
    }

    /**
     * Returns the date as a LocalDate object
     *
     * @param str The string
     * @returns The LocalDate object
     */
    public static LocalDate getLocalDateObject(String str) throws DateTimeParseException {
        return LocalDate.parse(str);
    }

    /**
     * Returns the string of form "YYYY-MM-DD" that contains date
     *
     * @param str The string of form "MMM dd YYYY"
     * @returns The string of form "YYYY-MM-DD"
     */
    public static String getDateOfInputForm(String str) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            return LocalDate.parse(str, formatter).toString();
        } catch (DateTimeParseException err) {
            System.out.println(err);
            return "";
        }
    }
}
