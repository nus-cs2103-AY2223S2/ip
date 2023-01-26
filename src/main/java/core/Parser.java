package core;
import exceptions.DukeException;
import exceptions.invalid.Input;

/**
 * Parses user input into usable terms for Duke.
 */
public class Parser {
    private String userInput;
    private String keyword = "";

    public enum KEYWORD {
        UNKNOWN, BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, SAVE
    }

    public Parser(String userInput) {
        this.userInput = userInput.trim();

        String[] split = userInput.split(" ", 2);
        if (split.length > 1) {
            this.userInput = split[1];
        } else {
            this.userInput = "";
        }
        this.keyword = split[0].toLowerCase().trim();
    }

    public String getKeyword() {
        return keyword;
    }

    public int extractIndexParams() throws DukeException {
        String[] userInSplit = this.userInput.split(" ",2);
        if (userInSplit.length < 1) {
            throw new exceptions.missing.Parameter(this.keyword);
        }
        try {
            int ind = Integer.parseInt(userInSplit[0]);
            return ind - 1; // Count starting from 0
        } catch (NumberFormatException e) {
            throw new exceptions.invalid.Input(String.format("%s is not an integer!", userInSplit[0]));
        }
    }

    public String[] extractTaskParams(Parser.KEYWORD desire) throws DukeException {
        // Check for RHS
        if (userInput.trim().isEmpty()) {
            throw new exceptions.missing.Parameter(this.keyword);
        }

        switch (desire) {
            case TODO: {
                // Return descriptor
                return new String[]{ userInput.trim() };
            }

            case EVENT: {
                // Check for /from keyword
                String[] fromSplit = this.splitByDelim(this.userInput,"/from",3);

                // Check for descriptor // This only allows "<DESC> /from <TIME> /to <TIME>"
                if (fromSplit[0].isEmpty()) {
                    throw new exceptions.missing.Parameter(this.keyword);
                }

                // Check from /to keyword
                String[] toSplit = this.splitByDelim(fromSplit[1],"/to",3);

                return new String[]{ fromSplit[0].trim() , toSplit[0].trim(), toSplit[1].trim() };
            }

            case DEADLINE: {
                // Check for /by keyword
                String[] bySplit = this.splitByDelim(this.userInput,"/by",3);

                // Check for descriptor
                if (bySplit[0].isEmpty()) {
                    throw new exceptions.missing.Parameter(this.keyword);
                }

                return new String[]{ bySplit[0].trim() , bySplit[1].trim()};
            }

            default:
                throw new exceptions.invalid.Input("DIO - extractTaskParams - used unexpectedly");
        }

    }

    private String[] splitByDelim(String in, String delim, int limit) throws DukeException{
        String[] ret = in.split(delim,limit);
        if (ret.length < 2) {
            throw new exceptions.missing.Parameter(this.keyword);
        } else if (ret.length > 2) {
            throw new exceptions.invalid.Input(
                    String.format("Multiple `%s` detected, only one is allowed, please try again.", delim));
        }
        return ret;
    }

    public static void parseFile(String[] task, TaskMaster tm) throws DukeException {
        switch (task[0]) {
            case "T":
                tm.addToDo(task[1], Boolean.valueOf(task[2]));
                break;
            case"D":
                tm.addDeadLine(task[1], Boolean.valueOf(task[2]), DateHandler.convert(task[3]));
                break;
            case "E":
                tm.addEvent(task[1], Boolean.valueOf(task[2]), DateHandler.convert(task[3]), DateHandler.convert(task[4]));
                break;
            default:
                throw new exceptions.invalid.Input(String.format("Unknown command for %s",task[0]));
        }
    }

    public String parse(TaskMaster tm) throws DukeException{
        String[] args;
//        System.out.println(this.getKeyword());
        switch (this.getKeyword()) {
            case "list":
                return tm.list();
            case "mark":
                return tm.markComplete(extractIndexParams(), true);
            case "unmark":
                return tm.markComplete(extractIndexParams(), false);
            case "todo":
                args = extractTaskParams(KEYWORD.TODO);
//                System.out.println(args);
                return tm.addToDo(args[0], false);
            case "event":
                args = extractTaskParams(KEYWORD.EVENT);
                return tm.addEvent(args[0], false, DateHandler.convert(args[1]), DateHandler.convert(args[2]));
            case "deadline":
                args = extractTaskParams(KEYWORD.DEADLINE);
                return tm.addDeadLine(args[0], false, DateHandler.convert(args[1]));
            case "delete":
                return tm.delete(extractIndexParams());
            case "save":
                DukeIO.writeSave(tm);
                return "Saved!";
            case "load":
                DukeIO.readSave(tm);
                return "Loaded!";
            case "bye":
                throw new exceptions.Quit();
            case "?":
                throw new exceptions.Unimplemented();
            default:
                throw new exceptions.invalid.Command();
        }
    }

}
