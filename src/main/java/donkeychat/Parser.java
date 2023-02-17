package donkeychat;

public class Parser {

    public Parser() {

    }
    /**
     * Parses user input.
     * @param currInput User input in string form.
     * @param taskList Current TaskList object.
     * @param storage  Current Storage object.
     * @param ui Current Ui object.
     * @return Whether to continue running the program.
     */
    public boolean parse(String currInput, TaskList taskList, Storage storage, Ui ui) {
        String[] splitInput = currInput.split(" ", 2);
        String currCommand = splitInput[0];
        assert taskList != null : "taskList must not be null!";
        assert storage != null : "storage must not be null!";
        assert ui != null : "ui must not be null!";
        Integer taskIndex;
        try {
            switch (currCommand) {
                case "bye":
                    ui.displayBye();
                    return false;
                case "list":
                    ui.displayTasks(taskList);
                    break;

                case "mark":
                    if (splitInput.length == 1) {
                        throw new DukeException("'mark' requires additional arguments!");
                    }
                    try {
                        taskIndex = Integer.valueOf(splitInput[1]) - 1;
                    } catch (NumberFormatException ex) {
                        ex.printStackTrace();
                        break;
                    }
                    if (taskIndex < 0 || taskIndex > taskList.getSize()) {
                        ui.displayText("invalid task index!");
                        break;
                    }
                    Task task = taskList.getAtIndex(taskIndex);
                    task.setDone(true);
                    ui.displayMarkTask(true, task);
                    break;
                case "unmark":
                    if (splitInput.length == 1) {
                        throw new DukeException("'unmark' requires additional arguments!");
                    }
                    try {
                        taskIndex = Integer.valueOf(splitInput[1]) - 1;
                    } catch (NumberFormatException ex) {
                        ex.printStackTrace();
                        break;
                    }
                    if (taskIndex < 0 || taskIndex > taskList.getSize()) {
                        System.out.println("invalid task index!");
                        break;
                    }
                    task = taskList.getAtIndex(taskIndex);
                    task.setDone(false);
                    ui.displayMarkTask(false, task);
                    break;
                case "todo":
                    if (splitInput.length == 1) {
                        throw new DukeException("'todo' requires additional arguments!");
                    }
                    taskList.addTask(new ToDo(splitInput[1]));
                    ui.displayAddTask(taskList);
                    break;
                case "deadline":
                    if (splitInput.length == 1) {
                        throw new DukeException("'deadline' requires additional arguments!");
                    }
                    Integer indexBy = splitInput[1].indexOf("/by ");
                    taskList.addTask(
                        new Deadline(splitInput[1].substring(0, indexBy - 1),
                            splitInput[1].substring(indexBy + 4)));
                    ui.displayAddTask(taskList);
                    break;
                case "event":
                    if (splitInput.length == 1) {
                        throw new DukeException("'event' requires additional arguments!");
                    }
                    Integer indexFrom = splitInput[1].indexOf("/from ");
                    Integer indexTo = splitInput[1].indexOf("/to ");
                    taskList.addTask(new Event(
                        splitInput[1].substring(0, indexFrom - 1),
                        splitInput[1].substring(indexFrom + 6, indexTo - 1),
                        splitInput[1].substring(indexTo + 4)));
                    ui.displayAddTask(taskList);
                    break;
                case "delete":
                    if (splitInput.length == 1) {
                        throw new DukeException("'delete' requires additional arguments!");
                    }
                    try {
                        taskIndex = Integer.valueOf(splitInput[1]) - 1;
                    } catch (NumberFormatException ex) {
                        ex.printStackTrace();
                        break;
                    }

                    if (taskIndex < 0 || taskIndex > taskList.getSize()) {
                        System.out.println("invalid task index!");
                        break;
                    }
                    ui.displayDeleteTask(taskList.getAtIndex(taskIndex));
                    taskList.removeAtIndex(taskIndex.intValue());
                    break;
                case "find":
                    if (splitInput.length == 1) {
                        throw new DukeException("'find' requires additional arguments!");
                    }
                    ui.displayMatchingTasks(taskList,splitInput[1]);

                    break;
                default:
                    throw new DukeException("Please enter a valid command!");
            }
            storage.updateSave(taskList);

        } catch (DukeException e) {
            ui.displayText(e.getMessage());
        }
        return true;
    }

}
