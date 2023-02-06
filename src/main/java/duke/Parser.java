package duke;

/**
 * Handles the reading and execution of inputs
 */
public class Parser {

    enum Types { LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, BYE, FIND }

    Parser() { }

    /**
     * Reads input from user and splits it into cases based on Types specified
     * in enum Types
     * @param inputString Input from user
     * @param taskList       List containing all current tasks
     * @param ui             Ui that runs output
     * @return Boolean based on input. Only returns true when command is bye,
     *     which exits the chat bot. Else, returns false which continues the chat bot
     * @throws NeroException Throws an exception depending on the exception faced
     */
    String parseCommand(String inputString, TaskList<Task> taskList, Ui ui) throws NeroException {
        try {
            String[] input = inputString.split(" ");
            switch (Enum.valueOf(Types.class, input[0].toUpperCase())) {
            case BYE:
                return ui.printExitInstructions();
            case LIST:
                return ui.printTasksMessage() + "\n" + taskList.printTasks();
            case MARK: {
                try {
                    int taskToMark = Integer.parseInt(input[1]) - 1;
                    Task currTask = taskList.get(taskToMark);
                    currTask.setAsDone();
                    return ui.printMarkedTaskMessage(currTask.toString());
                } catch (IndexOutOfBoundsException e) {
                    throw new NeroException("Please add the correct index from 0 to "
                            + taskList.getSize());
                }
            }
            case UNMARK: {
                try {
                    int taskToUnmark = Integer.parseInt(input[1]) - 1;
                    Task currTask = taskList.get(taskToUnmark);
                    currTask.setAsUndone();
                    return ui.printUnmarkedTaskMessage(currTask.toString());
                } catch (IndexOutOfBoundsException e) {
                    throw new NeroException("Please add the correct index from 0 to "
                            + taskList.getSize());
                }
            }
            case TODO:
                int index = inputString.indexOf("todo");
                try {
                    String description = inputString.substring(index + 5);
                    Task newTask = new ToDo(description);
                    taskList.addTask(newTask);
                    return ui.printAddedTasks(newTask.toString(), taskList.getSize());
                } catch (IndexOutOfBoundsException e) {
                    throw new NeroException("Description cannot be empty!!!");
                }
            case DEADLINE:
                try {
                    String[] cleanedString = cleanDeadline(inputString);
                    String description = cleanedString[0];
                    String duration = cleanedString[1];
                    Task newTask = new Deadline(description, duration);
                    taskList.addTask(newTask);
                    return ui.printAddedTasks(newTask.toString(), taskList.getSize());
                } catch (IndexOutOfBoundsException e) {
                    throw new NeroException("Add a task description and deadline in yyyy-mm-dd format!!!");
                }
            case EVENT:
                try {
                    String[] cleanedString = cleanEvent(inputString);
                    String description = cleanedString[0];
                    String startDate = cleanedString[1];
                    String endDate = cleanedString[2];
                    Task newTask = new Event(description, startDate, endDate);
                    taskList.addTask(newTask);
                    return ui.printAddedTasks(newTask.toString(), taskList.getSize());
                } catch (IndexOutOfBoundsException e) {
                    throw new NeroException("Add a task description, from and to date in yyyy-mm-dd format!!!");
                }
            case DELETE:
                try {
                    int toDelete = Integer.parseInt(input[1]) - 1;
                    Task removedTask = taskList.get(toDelete);
                    taskList.removeTask(toDelete);
                    return ui.printDeletedTasks(removedTask.toString(), taskList.getSize());
                } catch (IndexOutOfBoundsException e) {
                    throw new NeroException("Add a correct task number");
                }
            case FIND:
                TaskList<Task> newTaskList = new TaskList<>();
                for (int i = 0; i < taskList.getSize(); i++) {
                    if (taskList.get(i).containsWord(input[1])) {
                        newTaskList.addTask(taskList.get(i));
                    }
                }
                if (newTaskList.getSize() > 0) {
                    return ui.printMatchingTasks() + "\n" + newTaskList.printTasks();
                } else {
                    return ui.printNoMatchingTasks();
                }
            default:
                return "Command not detected! Please retry";
            }
        } catch (IllegalArgumentException e) {
            throw new NeroException("Wrong input!! Command not found!!");
        }
    }

    String[] cleanDeadline(String inputString) {
        String[] cleanedString = new String[2];
        String[] splitString = inputString.split("/");
        String description = splitString[0].replace("deadline", "");
        String duration = splitString[1].replace("by", "");
        cleanedString[0] = description;
        cleanedString[1] = duration;
        return cleanedString;
    }

    String[] cleanEvent(String inputString) {
        String[] cleanedString = new String[3];
        String[] splitString = inputString.split("/");
        String description = splitString[0].replace("event", "");
        String startDate = splitString[1].replace("from", "");
        String endDate = splitString[2].replace("to", "");
        cleanedString[0] = description;
        cleanedString[1] = startDate;
        cleanedString[2] = endDate;
        return cleanedString;
    }
}
