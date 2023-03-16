package seedu.duke;

/**
 * Class that parses the command given.
 *
 */
public class Parser {
    private enum Type {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND
    }


    public Parser() throws DukeException {

    }

    /**
     * Takes in the command and chooses what function should be used.
     *
     * @param input the command given.
     * @param inputArgs an array of String containing the words of the command given.
     * @param COUNTER int indicating list size
     * @param tasks TaskList object containing the tasks list and functions.
     */
    public String parse(String input, TaskList tasks) throws DukeException {
        String[] inputArgs = input.split(" ");
        Type t;
        try {
            t = Type.valueOf(inputArgs[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            return "Wrong command";
        }
        switch (t) {
            case LIST:
                return tasks.showList();

            case DEADLINE:
                return tasks.addDeadline(inputArgs,0);

            case TODO:
                return tasks.addToDo(inputArgs);

            case EVENT:
                return tasks.addEvent(inputArgs, 0);

            case DELETE:
                return tasks.delete(inputArgs);

            case MARK:
                return tasks.mark(inputArgs);

            case UNMARK:
                return tasks.unmark(inputArgs);

            case BYE:
                return "    Bye. Hope to see you again soon!";

            case FIND:
                return tasks.find(inputArgs);

            default:
                throw new DukeException();
        }



//        } else if (inputArgs.length < 2 && !input.equals("")) {
////            throw new DukeException();
//            return "I don't know what you are talking about";


//                return "I don't know what you are talking about";
            }




}
