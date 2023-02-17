package duke;

/**
 * Parses user's inputs into command line and uses the TaskList, Ui and Storage classes
 * to run operations.
 */
public class Parser {

    private final TaskList tasklist;
    private final Ui ui;

    private final Storage storage;

    public Parser(TaskList tasklist, Ui ui, Storage storage) {
        this.tasklist = tasklist;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Takes in user's input and obtains the first word to execute different command.
     * @param input User's input
     */
    public String parse(String input) {
        String str;
        try {
            String[] arr = input.split(" ", 2);
            boolean hasDetails = arr.length != 1;
            switch (arr[0]) {
            case "bye":
            case "exit":
                str = ui.exit();
                break;
            case "list":
            case "ls":
            case "l":
                str = tasklist.list();
                break;
            case "mark":
            case "m":
                if (!hasDetails) {
                    throw new DukeException("Please include the task index to mark");
                } else {
                    str = tasklist.setTaskStatus(Integer.parseInt(arr[1]), true);
                    break;
                }
            case "unmark":
            case "um":
                if (!hasDetails) {
                    throw new DukeException("Please include the task index to unmark.");
                }
                str = tasklist.setTaskStatus(Integer.parseInt(arr[1]), false);
                break;
            case "delete":
            case "rm":
                if (!hasDetails) {
                    throw new DukeException("Please include the task index to delete.");
                }
                str = tasklist.delete(Integer.parseInt(arr[1]));
                break;
            case "todo":
            case "t":
                if (!hasDetails) {
                    throw new DukeException("Please include the todo details.");
                }
                str = tasklist.addToDo(arr[1]);
                break;
            case "deadline":
            case "d":
                if (!hasDetails) {
                    throw new DukeException("Please include the deadline details.");
                }
                String[] descriptionBy = arr[1].split("/by", 2);
                if (descriptionBy.length == 1) {
                    throw new DukeException("Please insert deadline date after /by");
                }

                str = tasklist.addDeadline(descriptionBy[0].trim(), descriptionBy[1].trim());
                break;
            case "event":
            case "e":
                if (!hasDetails) {
                    throw new DukeException("Please include the event details.");
                }
                String[] descriptionOthers = arr[1].split("/from", 2);
                if (descriptionOthers.length == 1) {
                    throw new DukeException("Please insert the date the event takes place from, after /from ");
                }
                String[] fromTo = descriptionOthers[1].split("/to", 2);
                if (fromTo.length == 1) {
                    throw new DukeException("Please insert the date the event takes place until, after /to ");
                }
                str = tasklist.addEvent(descriptionOthers[0], fromTo[0], fromTo[1]);
                break;
            case "find":
            case "f":
                if (!hasDetails) {
                    throw new DukeException("Please include the task you would like to find.");
                }
                str = tasklist.find(arr[1]);
                break;
            default:
                throw new DukeException("Sorry, I don't know what that means.");
            }
            // handle changes to arraylist
            storage.writeFile(tasklist);
        } catch (DukeException e) {
            str = ui.showError(e);
        }
        return str;
    }
}
