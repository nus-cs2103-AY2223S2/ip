package duke;

public class Parser {

    private static int TASK_TYPE_INDEX = 0;
    private static int TASK_IS_DONE_INDEX = 1;
    private static int TASK_DESCRIPTION = 2;
    private static int START_TIME_INDEX = 3;
    private static int END_TIME_INDEX = 4;
    private static int TODO_TASK_DESCRIPTION_INDEX = 5;
    private static int DEADLINE_TASK_DESCRIPTION_INDEX = 9;
    private static int EVENT_TASK_DESCRIPTION_INDEX = 6;
    private static int TASK_NAME_INDEX = 0;
    private static int TASK_START_TIME_INDEX = 1;
    private static int TASK_END_TIME_INDEX = 2;
    private static int TO_REMOVE_LEADING_FOUR_LETTERS = 5;
    private static int TO_REMOVE_LEADING_TWO_LETTERS = 3;

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

    public static String getSearchWord(String line) {
        String searchWord = line.substring(TO_REMOVE_LEADING_FOUR_LETTERS);
        return searchWord;
    }
}
