package duke;

/**
 * Decodes the input base on the keyword the user input.
 */
public class Parser {

    private final TaskList taskList;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the event type associated to the user input
     *
     * @param input User input.
     * @return DukeKeyword
     * @throws DukeException If keyword is not recognised.
     */
    protected static DukeKeyword parse(String input) throws DukeException {
        String[] arr = input.split(" ");

        if (arr[0].equals("bye")) {
            return DukeKeyword.BYE;
        }
        if (arr[0].equals("list")) {
            return DukeKeyword.LIST;
        }
        if (arr[0].equals("mark")) {
            return DukeKeyword.MARK;
        }
        if (arr[0].equals("unmark")) {
            return DukeKeyword.UNMARK;
        }
        if (arr[0].equals("delete")) {
            return DukeKeyword.DELETE;
        }
        if (arr[0].equals("todo")) {
            return DukeKeyword.TODO;
        }
        if (arr[0].equals("deadline")) {
            return DukeKeyword.DEADLINE;
        }
        if (arr[0].equals("event")) {
            return DukeKeyword.EVENT;
        }
        if (arr[0].equals("find")) {
            return DukeKeyword.FIND;
        }
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public Command decode(String userInput) throws DukeException {
        String[] arr = userInput.split(" ", 2);

        if (arr[0].equals("bye")) {
            return new CommandBye();
        }
        if (arr[0].equals("list")) {
            return new CommandList(taskList);
        }
        if (arr[0].equals("mark")) {
            String index = arr[1];
            return new CommandMark(taskList, index);
        }
        if (arr[0].equals("unmark")) {
            String index = arr[1];
            return new CommandUnMark(taskList, index);
        }
        if (arr[0].equals("delete")) {
            String index = arr[1];
            return new CommandDelete(taskList, index);
        }
        if (arr[0].equals("todo")) {
            String taskDetails = arr[1];
            return new CommandToDo(taskList, taskDetails);
        }
        if (arr[0].equals("deadline")) {
            String taskDetails = arr[1];
            return new CommandDeadline(taskList, taskDetails);
        }
        if (arr[0].equals("event")) {
            String taskDetails = arr[1];
            return new CommandEvent(taskList, taskDetails);
        }
        if (arr[0].equals("find")) {
            String phrase = arr[1];
            return new CommandFind(taskList, phrase);
        }
        throw new DukeException(Ui.noKeywordMessage);
    }
}
