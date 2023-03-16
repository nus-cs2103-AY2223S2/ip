package seedu.duke;

/**
 * Class that parses the command given.
 *
 */
public class Parser {

    public static String LIST = "list";
    public static String MARK = "mark";
    public static String UNMARK = "unmark";
    public static String TODO = "todo";
    public static String DEADLINE = "deadline";
    public static String EVENT = "event";
    public static String FIND = "find";
    public static String DELETE = "delete";


    public Parser() throws DukeException {

    }

    /**
     * Takes in the command and chooses what function should be used.
     *
     * @param echo the command given.
     * @param echoSplit an array of String containing the words of the command given.
     * @param COUNTER int indicating list size
     * @param tasks TaskList object containing the tasks list and functions.
     */
    public String parse(String echo, String[] echoSplit, TaskList tasks) throws DukeException {

        if (echo.equals("list")) {
            return tasks.showList();
        } else if (echoSplit.length < 2 && !echo.equals("")) {
//            throw new DukeException();
            return "I don't know what you are talking about";
        } else if (echoSplit[0].equals("mark")) {
            int index = Integer.valueOf(echoSplit[1]) - 1;
            assert index + 1 > 0 : "index should not be less than 0";
            tasks.mark(index);
            return "Marked " + (index + 1);
        } else if (echoSplit[0].equals("unmark")) {
            int index = Integer.valueOf(echoSplit[1]) - 1;
            assert index + 1 > 0 : "index should not be less than 0";
            tasks.unmark(index);
            return "Unmarked " + (index+1);
        } else {

            if (echoSplit[0].equals("todo")) {
                tasks.addToDo(echoSplit);
                return "added task";
            } else if (echoSplit[0].equals("deadline")) {
                tasks.addDeadline(echoSplit, 0);
                return "added deadline task";
            } else if (echoSplit[0].equals("event")) {
                tasks.addEvent(echoSplit, 0);
                return "added event task";
            } else if (echoSplit[0].equals("delete")) {
                int index = Integer.valueOf(echoSplit[1]) - 1;
                assert index + 1 > 0 : "index should not be less than 0";
                tasks.delete(index);
                return "delete task " + (index+1);
            } else if (echoSplit[0].equals("find")) {
                return tasks.find(echoSplit);

            } else {

                System.out.println("I don't know what you are talking about");
                return "I don't know what you are talking about";
            }
        }

    }


}
