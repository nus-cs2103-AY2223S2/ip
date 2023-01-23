package seedu.duke;

/**
 * Class that parses the command given.
 *
 */
public class Parser {

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
    public void parse(String echo, String[] echoSplit, int COUNTER, TaskList tasks) throws DukeException {
        if (echo.equals("list")) {
            tasks.showList();
        } else if (echoSplit.length < 2 && !echo.equals("")) {
            throw new DukeException();
        } else if (echoSplit[0].equals("mark")) {
            int index = Integer.valueOf(echoSplit[1]) - 1;
            tasks.mark(index);
        } else if (echoSplit[0].equals("unmark")) {
            int index = Integer.valueOf(echoSplit[1]) - 1;
            tasks.unmark(index);
        } else {
            if (echoSplit[0].equals("todo")) {
                tasks.addToDo(echoSplit);
                COUNTER++;
            } else if (echoSplit[0].equals("deadline")) {
                tasks.addDeadline(echoSplit, 0);
                COUNTER++;
            } else if (echoSplit[0].equals("event")) {
                tasks.addEvent(echoSplit, 0);
                COUNTER++;
            } else if (echoSplit[0].equals("delete")) {
                int index = Integer.valueOf(echoSplit[1]) - 1;
                tasks.delete(index);
                COUNTER--;
            } else {

            }
        }

    }


}
