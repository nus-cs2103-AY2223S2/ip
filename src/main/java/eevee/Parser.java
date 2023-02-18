package eevee;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Parser {

    private final static int TASK_TYPE_INDEX = 0;
    private final static int TASK_IS_DONE_INDEX = 1;
    private final static int TASK_DESCRIPTION = 2;
    private final static int START_TIME_INDEX = 3;
    private final static int END_TIME_INDEX = 4;
    private final static int TODO_TASK_DESCRIPTION_INDEX = 5;
    private final static int DEADLINE_TASK_DESCRIPTION_INDEX = 9;
    private final static int EVENT_TASK_DESCRIPTION_INDEX = 6;
    private final static int TASK_NAME_INDEX = 0;
    private final static int TASK_START_TIME_INDEX = 1;
    private final static int TASK_END_TIME_INDEX = 2;
    private final static int TO_REMOVE_LEADING_FOUR_LETTERS = 5;
    private final static int TO_REMOVE_LEADING_TWO_LETTERS = 3;

    /**
     * Converts a task from the task list format
     * to a Task object
     * @param line line to be converted to a <code>Task</code>
     * @return a <code>Task</code>> object with the relevant information
     */
    public static Task convertTaskFromLineInTaskList(String line) {
        String[] taskInfo = line.split("[|]");
        String taskType = taskInfo[TASK_TYPE_INDEX].trim();
        boolean isDone = taskInfo[TASK_IS_DONE_INDEX].trim().equals("1");
        String taskDescription = taskInfo[TASK_DESCRIPTION].trim();
        switch (taskType) {
        case "T":
            return new ToDos(taskDescription, isDone);
        case "D":
            String taskTime = taskInfo[START_TIME_INDEX].trim();
            return new Deadlines(taskDescription, taskTime, isDone);
        case "E":
            String startTime = taskInfo[START_TIME_INDEX].trim();
            String endTime = taskInfo[END_TIME_INDEX].trim();
            return new Events(taskDescription, startTime, endTime, isDone);
        default:
            throw new IllegalStateException("Unexpected value: " + taskType);
        }
    }

    /**
     * Makes a <code>ToDos</code> object from the given line.
     * @param line information of the <code>ToDos</code> in a
     *             <code>String</code> format
     * @return a <code>Task</code> object of the <code>ToDos</code>
     */
    public static Task makeTodoFromCommand(String line) {
        String taskName = line.substring(TODO_TASK_DESCRIPTION_INDEX);
        return new ToDos(taskName);
    }

    /**
     * Makes a <code>Deadlines</code> object from the given line.
     * @param line information of the <code>Deadlines</code> in a
     *             <code>String</code> format
     * @return a <code>Task</code> object of the <code>Deadlines</code>
     */
    public static Task makeDeadlineFromCommand(String line) {
        String taskInfo = line.substring(DEADLINE_TASK_DESCRIPTION_INDEX);
        String taskName = taskInfo.split(" /by")[TASK_NAME_INDEX];
        String taskDeadline = taskInfo.split("/by ")[TASK_START_TIME_INDEX];
        return new Deadlines(taskName, taskDeadline);
    }

    /**
     * Makes a <code>Events</code> object from the given line.
     * @param line information of the <code>Events</code> in a
     *             <code>String</code> format
     * @return a <code>Task</code> object of the <code>Events</code>
     */
    public static Task makeEventFromCommand(String line) {
        String taskInfo = line.substring(EVENT_TASK_DESCRIPTION_INDEX);
        String taskName = taskInfo.split(" /")[TASK_NAME_INDEX];
        String[] taskInfoTimes = taskInfo.split(" /");
        String taskStart = taskInfoTimes[TASK_START_TIME_INDEX].substring(TO_REMOVE_LEADING_FOUR_LETTERS);
        String taskEnd = taskInfoTimes[TASK_END_TIME_INDEX].substring(TO_REMOVE_LEADING_TWO_LETTERS);
        return new Events(taskName, taskStart, taskEnd);
    }

    /**
     * Gets the search word from the given line of command.
     * @param line line of command containing the search word
     * @return the search word in a String
     */
    public static String getSearchWord(String line) {
        return line.substring(TO_REMOVE_LEADING_FOUR_LETTERS);
    }

    /**
     * Gets the index needed from the given command.
     * @param command the user command
     * @return the index in the form of an <code>int</code>
     */
    public static int getIndexFromCommand(String command) {
        return Integer.parseInt(command.split(" ")[1]) - 1;
    }

    /**
     * Gets the type of command from the given command.
     * @param command the user command
     * @return the type of command in the form of a String
     */
    public static String getTypeOfCommand(String command) {
        return command.split(" ")[0];
    }
    
    public static String handleInput(String command, Ui ui, TaskList tasks, Storage storage) throws EeveeException,
            IOException, IndexOutOfBoundsException, DateTimeParseException {
        String typeOfCommand = getTypeOfCommand(command);
        switch (typeOfCommand) {
        case "bye":
            return ui.sayBye();
        case "list":
            return ui.showTaskList(tasks);
        case "mark":
            Task markedTask = tasks.markTaskAsDone(getIndexFromCommand(command), storage);
            assert markedTask.getTaskStatus() : "Task is wrongly marked.";
            return ui.showMarkingTaskDone(markedTask);
        case "unmark":
            Task unmarkedTask = tasks.markTaskAsUndone(getIndexFromCommand(command), storage);
            assert !unmarkedTask.getTaskStatus() : "Task is wrongly marked.";
            return ui.showMarkingTaskUndone(unmarkedTask);
        case "todo":
            Task newTodo = Parser.makeTodoFromCommand(command);
            tasks.addTask(newTodo, storage);
            return ui.showAddingNewTask(newTodo, tasks);
        case "deadline":
            Task newDeadline = Parser.makeDeadlineFromCommand(command);
            tasks.addTask(newDeadline, storage);
            return ui.showAddingNewTask(newDeadline, tasks);
        case "event":
            Task newEvent = Parser.makeEventFromCommand(command);
            boolean canAddEvent = tasks.canAddTask(newEvent, storage);
            return canAddEvent ? ui.showAddingNewTask(newEvent, tasks) : ui.showFailAddingNewTask(tasks);
        case "delete":
            Task taskToDelete = tasks.deleteTask(getIndexFromCommand(command), storage);
            return ui.showDeletingTask(taskToDelete, tasks);
        case "find":
            String searchWord = Parser.getSearchWord(command);
            TaskList tasksFound = tasks.makeTaskFinder(searchWord);
            return ui.showFindingTask(tasksFound);
        default:
            throw new EeveeException("Command not recognised.");
        }
    }
}
