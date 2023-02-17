package core;

import exceptions.DukeException;

/**
 * Parses user input into usable terms for core.Duke.
 * @author EL
 */
public class Parser {
    private String userInput;
    private final String keyword;

    /**
     * For enum attempt.
     */
    public enum Keyword {
        TODO, DEADLINE, EVENT, FIND
    }

    /**
     * This is the constructor method for Parser class.
     *
     * @param userInput The command to parse
     */
    public Parser(String userInput) {

        // When execution comes to this point, user input can't be empty!
        assert(userInput.isEmpty() != true);

        this.userInput = userInput.trim();
        String[] split = this.userInput.split(" ", 2);
        if (split.length > 1) {
            this.userInput = split[1];
        } else {
            this.userInput = "";
        }
        this.keyword = split[0].toLowerCase().trim();
    }

    /**
     * This method returns first word of user input.
     * For example 'mark 1', 'mark' would be considered as the keyword.
     *
     * @return The command which the user has typed.
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * This method returns integer from user input.
     * This assumes that the second item in the user input is an integer.
     * Example: 'mark 1', '1' is expected to be an integer.
     *
     * @return The integer value which the user has passed in.
     * @throws DukeException Thrown when user input is invalid or missing.
     */
    public int extractIndexParams() throws DukeException {
        String[] userInSplit = this.userInput.split(" ", 2);
        if (userInSplit.length < 1) {
            throw new exceptions.missing.Parameter(this.keyword);
        }
        try {
            int userSuppliedIndex = Integer.parseInt(userInSplit[0]);
            return userSuppliedIndex - 1; // Count starting from 0
        } catch (NumberFormatException e) {
            throw new exceptions.invalid.Input(String.format("%s is not an integer!", userInSplit[0]));
        }
    }

    /**
     * This method extracts commands for task creation.
     *
     * @param desire Which Task is being called
     * @return The appropriate inputs needed to create a Task
     * @throws DukeException Thrown when there is invalid input or missing parameters
     */
    public String[] extractTaskParams(Keyword desire) throws DukeException {
        // Check for RHS
        if (userInput.trim().isEmpty()) {
            throw new exceptions.missing.Parameter(this.keyword);
        }

        switch (desire) {
        case FIND: //intentionally hook onto TODO
        case TODO: {
            // Return descriptor
            return new String[]{userInput.trim()};
        }

        case EVENT: {
            // Check for /from keyword
            String[] fromSplit = this.splitByDelimiter(this.userInput, "/from", 3);

            // Check for descriptor // This only allows "<DESC> /from <TIME> /to <TIME>"
            if (fromSplit[0].isEmpty()) {
                throw new exceptions.missing.Parameter(this.keyword);
            }

            // Check from /to keyword
            String[] toSplit = this.splitByDelimiter(fromSplit[1], "/to", 3);

            return new String[]{fromSplit[0].trim(), toSplit[0].trim(), toSplit[1].trim()};
        }

        case DEADLINE: {
            // Check for /by keyword
            String[] bySplit = this.splitByDelimiter(this.userInput, "/by", 3);

            // Check for descriptor
            if (bySplit[0].isEmpty()) {
                throw new exceptions.missing.Parameter(this.keyword);
            }

            return new String[]{bySplit[0].trim(), bySplit[1].trim()};
        }

        default:
            throw new exceptions.invalid.Input("DIO - extractTaskParams - used unexpectedly");
        }

    }

    /**
     * This method splits user input based on delimiter.
     * Example:
     * splitByDelimiter("Gymkhana /from TIME /to TIME","/from",2)
     * returns ["Gymkhana","TIME /to TIME"]
     *
     * @param in        The string to split
     * @param delimiter The delimiter to watch for
     * @param limit     What is the maximum times the method should split
     * @return String array with limit number of entries
     * @throws DukeException Thrown when missing parameters or invalid input is detected
     */
    private String[] splitByDelimiter(String in, String delimiter, int limit) throws DukeException {
        String[] ret = in.split(delimiter, limit);
        if (ret.length < 2) {
            throw new exceptions.missing.Parameter(this.keyword);
        } else if (ret.length > 2) {
            throw new exceptions.invalid.Input(
                    String.format("Multiple `%s` detected, only one is allowed, please try again.", delimiter));
        }
        return ret;
    }

    /**
     * This method parses save file and loads it into the given TaskMaster.
     *
     * @param task The task stored in the safe file
     * @param tm   The runtime TaskMaster object
     * @throws DukeException Thrown when input is invalid, which can appear when user manually edits the file.
     */
    public static void parseSaveFile(String[] task, TaskMaster tm) throws DukeException {
        switch (task[0]) {
        case "T":
            tm.addToDo(task[1], Boolean.parseBoolean(task[2]));
            break;
        case "D":
            tm.addDeadLine(task[1], Boolean.parseBoolean(task[2]),
                    DateHandler.parse(task[3]));
            break;
        case "E":
            tm.addEvent(task[1], Boolean.parseBoolean(task[2]),
                    DateHandler.parse(task[3]), DateHandler.parse(task[4]));
            break;
        default:
            throw new exceptions.invalid.Input(String.format("Unknown command for %s", task[0]));
        }
    }

    /**
     * This method parses user input into meaningful commands.
     *
     * @param tm The runtime TaskMaster object
     * @return Message from the corresponding commands to print out.
     * @throws DukeException Thrown when missing parameters is found or index error etc.
     */
    public String parse(TaskMaster tm) throws DukeException {
        String[] args;
        switch (this.getKeyword()) {
        case "list":
            return tm.listAllTasks();
        case "mark":
            return tm.markComplete(extractIndexParams(), true);
        case "unmark":
            return tm.markComplete(extractIndexParams(), false);
        case "todo":
            args = extractTaskParams(Keyword.TODO);
            return tm.addToDo(args[0], false);
        case "event":
            args = extractTaskParams(Keyword.EVENT);
            return tm.addEvent(args[0], false, DateHandler.parse(args[1]), DateHandler.parse(args[2]));
        case "deadline":
            args = extractTaskParams(Keyword.DEADLINE);
            return tm.addDeadLine(args[0], false, DateHandler.parse(args[1]));
        case "delete":
            return tm.deleteTask(extractIndexParams());
        case "save":
            DukeIO.writeSave(tm);
            return "Saved!";
        case "load":
            DukeIO.readSave(tm);
            return "Loaded!";
        case "bye":
            throw new exceptions.Quit();
        case "find":
            return tm.findTask(extractTaskParams(Keyword.FIND)[0]);
        case "help":
            return helpMessage();
        case "l33tmagic":
            throw new exceptions.Unimplemented();
        default:
            throw new exceptions.invalid.Command();
        }
    }

    /**
     * This method returns the help message to be printed.
     * @return Returns the help message.
     */
    private static String helpMessage() {
        String ret = "List: Lists all file"
                + "mark [integer]\n"
                + "unmark [integer]\n"
                + "todo [description]\n"
                + "deadline [description] /by [yyyy/MM/dd HHmm]\n"
                + "event [description] /from [yyyy/MM/dd HHmm] /to [yyyy/MM/dd HHmm]\n"
                + "find [keyword]\n"
                + "delete [integer]\n"
                + "save\n"
                + "load\n"
                + "bye\n"
                + "help";
        return ret;
    }

}
