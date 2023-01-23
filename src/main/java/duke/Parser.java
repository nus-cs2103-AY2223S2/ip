package duke;
public class Parser {

    private final TaskList tasklist;
    private final Ui ui;

    private final Storage storage;

    public Parser(TaskList tasklist, Ui ui, Storage storage) {
        this.tasklist = tasklist;
        this.ui = ui;
        this.storage = storage;
    }

    public void parse(String input) {
        try {
            String[] arr = input.split(" ", 2);
            boolean details = arr.length != 1;
            switch (arr[0]) {
                case "bye":
                    ui.exit();
                    break;
                case "list":
                    tasklist.list();
                    break;
                case "mark":
                    if (!details) {
                        throw new DukeException("Please include the task index to mark");
                    } else {
                        tasklist.setTaskStatus(Integer.parseInt(arr[1]), true);
                        break;
                    }
                case "unmark":
                    if (!details) {
                        throw new DukeException("Please include the task index to unmark.");
                    }
                    tasklist.setTaskStatus(Integer.parseInt(arr[1]), false);
                    break;
                case "delete":
                    if (!details) {
                        throw new DukeException("Please include the task index to delete.");
                    }
                    tasklist.delete(Integer.parseInt(arr[1]));
                    break;
                case "todo":
                    if (!details) {
                        throw new DukeException("Please include the todo details.");
                    }
                    tasklist.addToDo(arr[1]);
                    break;
                case "deadline":
                    if (!details) {
                        throw new DukeException("Please include the deadline details.");
                    }
                    String[] descriptionBy = arr[1].split("/by", 2);
                    if (descriptionBy.length == 1) {
                        throw new DukeException("Please insert deadline date after /by");
                    }

                    tasklist.addDeadline(descriptionBy[0], descriptionBy[1]);
                    break;
                case "event":
                    if (!details) {
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
                    tasklist.addEvent(descriptionOthers[0], fromTo[0], fromTo[1]);
                    break;
                default:
                    throw new DukeException("Sorry, I don't know what that means.");
            }

            // handle changes to arraylist
            storage.writeFile(tasklist);
        } catch (DukeException e) {
            ui.showError(e);
        }
    }

}
